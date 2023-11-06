/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.program.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.maibaduoduo.program.service.ProgramService;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.publisher.StartProgramEventPublisher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.program.entity.ProgramEntity;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;

/**
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-05-06 20:24:36
 */
@RestController
@RequestMapping("program/program")
@Api("制造执行计划")
public class ProgramController {
    @Autowired
    private ProgramService programService;
    @Autowired
    private StartProgramEventPublisher programEventPublisher;
    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("program:program:list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = programService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("program:program:info")
    @ApiOperation("信息")
    public R info(@PathVariable("id") Long id){
		ProgramEntity program = programService.getById(id);

        return R.ok().put("program", program);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("program:program:save")
    @ApiOperation("保存")
    public R save(@RequestBody ProgramEntity program){
        //programService.save(program);
        programEventPublisher.publishEvent(new ProgramTask(),2);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        programEventPublisher.publishEvent(new ProgramTask(),100);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("program:program:update")
    @ApiOperation("修改")
    public R update(@RequestBody ProgramEntity program){
		programService.updateById(program);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("program:program:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody Long[] ids){
		programService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
