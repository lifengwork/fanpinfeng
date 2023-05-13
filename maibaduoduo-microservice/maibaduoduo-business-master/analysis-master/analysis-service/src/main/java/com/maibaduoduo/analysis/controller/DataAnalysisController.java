/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.analysis.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.analysis.entity.DataAnalysisEntity;
import com.maibaduoduo.analysis.service.DataAnalysisService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;


/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-05-13 21:00:36
 */
@RestController
@RequestMapping("dataanalysis/dataanalysis")
public class DataAnalysisController {
    @Autowired
    private DataAnalysisService dataAnalysisService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dataanalysis:dataanalysis:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dataAnalysisService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("dataanalysis:dataanalysis:info")
    public R info(@PathVariable("id") Long id){
		DataAnalysisEntity dataAnalysis = dataAnalysisService.getById(id);

        return R.ok().put("dataAnalysis", dataAnalysis);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("dataanalysis:dataanalysis:save")
    public R save(@RequestBody DataAnalysisEntity dataAnalysis){
		dataAnalysisService.save(dataAnalysis);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("dataanalysis:dataanalysis:update")
    public R update(@RequestBody DataAnalysisEntity dataAnalysis){
		dataAnalysisService.updateById(dataAnalysis);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("dataanalysis:dataanalysis:delete")
    public R delete(@RequestBody Long[] ids){
		dataAnalysisService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
