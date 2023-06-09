package com.xxl.job.saas.controller;

import com.xxl.job.saas.controller.annotation.PermessionLimit;
import com.xxl.job.saas.core.model.XxlJobGroup;
import com.xxl.job.saas.core.model.XxlJobInfo;
import com.xxl.job.saas.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.saas.core.thread.JobTriggerPoolHelper;
import com.xxl.job.saas.core.trigger.TriggerTypeEnum;
import com.xxl.job.saas.dao.XxlJobGroupDao;
import com.xxl.job.saas.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * index controller
 *
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {

    @Resource
    private XxlJobGroupDao xxlJobGroupDao;
    @Resource
    private XxlJobService xxlJobService;

    @RequestMapping("/index1")
    public String index1(Model model, @RequestParam(required = false, defaultValue = "-1") Integer jobGroup) {

        // 枚举-字典
        model.addAttribute("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values());    // 路由策略-列表
        model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());                                // Glue类型-字典
        model.addAttribute("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values());    // 阻塞处理策略-字典

        // 任务组
        List<XxlJobGroup> jobGroupList = xxlJobGroupDao.findAll();
        model.addAttribute("JobGroupList", jobGroupList);
        model.addAttribute("jobGroup", jobGroup);

        return "jobinfo/jobinfo.index1";
    }

    @RequestMapping("/index2")
    public String index2(Model model, @RequestParam(required = false, defaultValue = "-1") Integer jobGroup) {

        // 枚举-字典
        model.addAttribute("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values());    // 路由策略-列表
        model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());                                // Glue类型-字典
        model.addAttribute("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values());    // 阻塞处理策略-字典

        // 任务组
        List<XxlJobGroup> jobGroupList = xxlJobGroupDao.findAll();
        model.addAttribute("JobGroupList", jobGroupList);
        model.addAttribute("jobGroup", jobGroup);

        return "jobinfo/jobinfo.index2";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") Integer start,
                                        @RequestParam(required = false, defaultValue = "10") Integer length,
                                        Integer jobGroup, String jobDesc, String executorHandler, String filterTime, Integer type) {
        return xxlJobService.pageList(start, length, jobGroup, jobDesc, executorHandler, filterTime, type);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnT<String> add(XxlJobInfo jobInfo) {
        return xxlJobService.add(jobInfo);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(XxlJobInfo jobInfo) {
        return xxlJobService.update(jobInfo);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnT<String> remove(Integer id) {
        return xxlJobService.remove(id);
    }

    @RequestMapping("/stop")        //  pause >> stop
    @ResponseBody
    public ReturnT<String> pause(Integer id) {
        return xxlJobService.stop(id);
    }

    @RequestMapping("/start")        //  resume >> start
    @ResponseBody
    public ReturnT<String> start(Integer id) {
        return xxlJobService.start(id);
    }

    /**
     * 触发任务
     *
     * @param id
     * @param executorParam
     * @return
     */
    @RequestMapping("/trigger")
    @ResponseBody
    //@PermessionLimit(limit = false)
    public ReturnT<String> triggerJob(int id, String executorParam) {
        // force cover job param
        if (executorParam == null) {
            executorParam = "";
        }

        JobTriggerPoolHelper.trigger(id, TriggerTypeEnum.MANUAL, -1, null, executorParam);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/addTimingTask")
    @ResponseBody
    @PermessionLimit(limit = false)
    public ReturnT<String> addTimingTask(@RequestBody XxlJobInfo dto) {
        return xxlJobService.addStart(dto);
    }
}
