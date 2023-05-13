/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.analysis.item.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.analysis.item.entity.ProgramItemEntity;
import com.maibaduoduo.analysis.item.service.ProgramItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-05-06 20:24:36
 */
@RestController
@RequestMapping("programitem/programitem")
@Api("执行计划项")
public class ProgramItemController {
    @Autowired
    private ProgramItemService programItemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("programitem:programitem:list")
    @ApiOperation("执行计划项列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = programItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("programitem:programitem:info")
    @ApiOperation("执行计划项信息")
    public R info(@PathVariable("id") Long id){
		ProgramItemEntity programItem = programItemService.getById(id);

        return R.ok().put("programItem", programItem);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("programitem:programitem:save")
    @ApiOperation("执行计划项保存")
    public R save(@RequestBody ProgramItemEntity programItem){
		programItemService.save(programItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("programitem:programitem:update")
    @ApiOperation("执行计划项修改")
    public R update(@RequestBody ProgramItemEntity programItem){
		programItemService.updateById(programItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("programitem:programitem:delete")
    @ApiOperation("执行计划项删除")
    public R delete(@RequestBody Long[] ids){
		programItemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
