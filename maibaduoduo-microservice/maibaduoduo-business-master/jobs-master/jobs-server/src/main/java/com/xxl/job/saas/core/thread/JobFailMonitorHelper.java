package com.xxl.job.saas.core.thread;

import com.xxl.job.saas.core.conf.XxlJobAdminConfig;
import com.xxl.job.saas.core.model.XxlJobGroup;
import com.xxl.job.saas.core.model.XxlJobInfo;
import com.xxl.job.saas.core.model.XxlJobLog;
import com.xxl.job.saas.core.trigger.TriggerTypeEnum;
import com.xxl.job.saas.core.util.I18nUtil;
import com.xxl.job.saas.core.util.MailUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * job monitor instance
 *
 * @author xuxueli 2015-9-1 18:05:56
 */
public class JobFailMonitorHelper {
    // email alarm template
    private static final String MAIL_BODY_TEMPLATE = "<h5>" + I18nUtil.getString("jobconf_monitor_detail") + "：</span>" +
            "<table border=\"1\" cellpadding=\"3\" style=\"border-collapse:collapse; width:80%;\" >\n" +
            "   <thead style=\"font-weight: bold;color: #ffffff;background-color: #ff8c00;\" >" +
            "      <tr>\n" +
            "         <td width=\"20%\" >" + I18nUtil.getString("jobinfo_field_jobgroup") + "</td>\n" +
            "         <td width=\"10%\" >" + I18nUtil.getString("jobinfo_field_id") + "</td>\n" +
            "         <td width=\"20%\" >" + I18nUtil.getString("jobinfo_field_jobdesc") + "</td>\n" +
            "         <td width=\"10%\" >" + I18nUtil.getString("jobconf_monitor_alarm_title") + "</td>\n" +
            "         <td width=\"40%\" >" + I18nUtil.getString("jobconf_monitor_alarm_content") + "</td>\n" +
            "      </tr>\n" +
            "   </thead>\n" +
            "   <tbody>\n" +
            "      <tr>\n" +
            "         <td>{0}</td>\n" +
            "         <td>{1}</td>\n" +
            "         <td>{2}</td>\n" +
            "         <td>" + I18nUtil.getString("jobconf_monitor_alarm_type") + "</td>\n" +
            "         <td>{3}</td>\n" +
            "      </tr>\n" +
            "   </tbody>\n" +
            "</table>";
    private static Logger logger = LoggerFactory.getLogger(JobFailMonitorHelper.class);
    private static JobFailMonitorHelper instance = new JobFailMonitorHelper();

    // ---------------------- monitor ----------------------
    /**
     * 有序阻塞队列
     */
    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(0xfff8);

    private Thread monitorThread;
    private volatile boolean toStop = false;

    public static JobFailMonitorHelper getInstance() {
        return instance;
    }

    // producer
    public static void monitor(int jobLogId) {
        getInstance().queue.offer(jobLogId);
    }

    public void start() {
        monitorThread = new Thread(new Runnable() {

            @Override
            public void run() {
                // monitor
                while (!toStop) {
                    try {
                        List<Integer> jobLogIdList = new ArrayList<Integer>();
                        //一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数），
                        //　　　　通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。
                        int drainToNum = JobFailMonitorHelper.instance.queue.drainTo(jobLogIdList);

                        if (CollectionUtils.isNotEmpty(jobLogIdList)) {
                            for (Integer jobLogId : jobLogIdList) {
                                if (jobLogId == null || jobLogId == 0) {
                                    continue;
                                }
                                XxlJobLog log = XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().load(jobLogId);
                                if (log == null) {
                                    continue;
                                }
								/*
								 判断执行成功的字段有2个,xxl_job_qrtz_trigger_log 表的TriggerCode=调度器调度状态 、HandleCode=执行器执行状态

								  */
                                if (IJobHandler.SUCCESS.getCode() == log.getTriggerCode() && log.getHandleCode() == 0) {
                                    // job running
                                    JobFailMonitorHelper.monitor(jobLogId);
                                    logger.debug(">>>>>>>>>>> job monitor, job running, JobLogId:{}", jobLogId);
                                } else if (IJobHandler.SUCCESS.getCode() == log.getHandleCode()) {
                                    // job success, pass
                                    logger.info(">>>>>>>>>>> job monitor, job success, JobLogId:{}", jobLogId);
                                } else /*if (IJobHandler.FAIL.getCode() == log.getTriggerCode()
										|| IJobHandler.FAIL.getCode() == log.getHandleCode()
										|| IJobHandler.FAIL_RETRY.getCode() == log.getHandleCode() )*/ {

                                    // job fail,

                                    // 1、fail retry
                                    XxlJobInfo info = XxlJobAdminConfig.getAdminConfig().getXxlJobInfoDao().loadById(log.getJobId());

                                    if (log.getExecutorFailRetryCount() > 0) {
                                        JobTriggerPoolHelper.trigger(log.getJobId(), TriggerTypeEnum.RETRY, (log.getExecutorFailRetryCount() - 1), log.getExecutorShardingParam(), null);
                                        String retryMsg = "<br><br><span style=\"color:#F39C12;\" > >>>>>>>>>>>" + I18nUtil.getString("jobconf_trigger_type_retry") + "<<<<<<<<<<< </span><br>";
                                        log.setTriggerMsg(log.getTriggerMsg() + retryMsg);
                                        XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().updateTriggerInfo(log);
                                    }

                                    // 2、fail alarm
                                    failAlarm(info, log);

                                    logger.info(">>>>>>>>>>> job monitor, job fail, JobLogId:{}", jobLogId);
                                }/* else {
									JobFailMonitorHelper.monitor(jobLogId);
									logger.info(">>>>>>>>>>> job monitor, job status unknown, JobLogId:{}", jobLogId);
								}*/
                            }
                        }

                        TimeUnit.SECONDS.sleep(10);
                    } catch (Exception e) {
                        logger.error("job monitor error:{}", e);
                    }
                }

                // monitor all clear
                List<Integer> jobLogIdList = new ArrayList<Integer>();
                int drainToNum = getInstance().queue.drainTo(jobLogIdList);
                if (jobLogIdList != null && jobLogIdList.size() > 0) {
                    for (Integer jobLogId : jobLogIdList) {
                        XxlJobLog log = XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().load(jobLogId);
                        if (ReturnT.FAIL_CODE == log.getTriggerCode() || ReturnT.FAIL_CODE == log.getHandleCode()) {
                            // job fail,
                            XxlJobInfo info = XxlJobAdminConfig.getAdminConfig().getXxlJobInfoDao().loadById(log.getJobId());

                            failAlarm(info, log);
                            logger.info(">>>>>>>>>>> job monitor last, job fail, JobLogId:{}", jobLogId);
                        }
                    }
                }

            }
        });
        monitorThread.setDaemon(true);//守护线程
        monitorThread.start();
    }


    // ---------------------- alarm ----------------------

    public void toStop() {
        toStop = true;
        // interrupt and wait
        monitorThread.interrupt();
        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * fail alarm
     *
     * @param jobLog
     */
    private void failAlarm(XxlJobInfo info, XxlJobLog jobLog) {

        // send monitor email
        if (info != null && info.getAlarmEmail() != null && info.getAlarmEmail().trim().length() > 0) {

            String alarmContent = "Alarm Job LogId=" + jobLog.getId();
            if (jobLog.getTriggerCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>TriggerMsg=" + jobLog.getTriggerMsg();
            }
            if (jobLog.getHandleCode() > 0 && jobLog.getHandleCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>HandleCode=" + jobLog.getHandleMsg();
            }

            Set<String> emailSet = new HashSet<String>(Arrays.asList(info.getAlarmEmail().split(",")));
            for (String email : emailSet) {
                XxlJobGroup group = XxlJobAdminConfig.getAdminConfig().getXxlJobGroupDao().load(Integer.valueOf(info.getJobGroup()));

                String title = I18nUtil.getString("jobconf_monitor");
                String content = MessageFormat.format(MAIL_BODY_TEMPLATE,
                        group != null ? group.getTitle() : "null",
                        info.getId(),
                        info.getJobDesc(),
                        alarmContent);

                MailUtil.sendMail(email, title, content);
            }
        }

        //  自定义报警策略， 如短信

    }

}
