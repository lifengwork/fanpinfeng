package com.maibaduoduo.task;

import com.maibaduoduo.common.service.SystemService;
import com.maibaduoduo.web.sys.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
//@Lazy(false)
public class DemoTask {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemService systemService;

    @Autowired
    private LogService logService;

    //@Scheduled(cron = "0 15 4 ? * MON-FRI")
    @Scheduled(cron = "0/30 10 * * * ?")
    public void testTask() {
    }

    @Scheduled(cron = "0 59 23 * * ? ")
    public void cleanInvalidOrder() {
        ;

    }

    @Scheduled(cron = "0 15 4 ? * MON-FRI")
    public void deleteSevenDaysAgo() {
    }

    /**
     * 月销量
     */
    @Scheduled(cron = "0 10 4 * * ? ")
    //@Scheduled(cron = "0/5 * * * * ?")
    public void monthSaleCount() {
    }

    /**
     * 累计销量
     */
    @Scheduled(cron = "0 10 3 * * ? ")
    //@Scheduled(cron = "0/5 * * * * ?")
    public void totalSaleCount() {
    }

    /**
     * 累计评价
     */
    @Scheduled(cron = "0 10 2 * * ? ")
    //@Scheduled(cron = "0/5 * * * * ?")
    public void totalEvaluateCount() {
    }
}
