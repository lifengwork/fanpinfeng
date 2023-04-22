package com.maibaduoduo.jobs.api.fallback;

import com.maibaduoduo.jobs.api.JobsTimingApi;
import com.maibaduoduo.jobs.common.R;
import com.maibaduoduo.jobs.dto.XxlJobInfo;
import org.springframework.stereotype.Component;

/**
 * 定时API 熔断
 *
 * @author admin
 * @date 2019/07/16
 */
@Component
public class JobsTimingApiFallback implements JobsTimingApi {
    @Override
    public R<String> addTimingTask(XxlJobInfo xxlJobInfo) {
        return R.timeout();
    }
}
