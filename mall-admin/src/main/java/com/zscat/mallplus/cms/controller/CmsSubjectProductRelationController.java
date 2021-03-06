package com.zscat.mallplus.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zscat.mallplus.annotation.SysLog;
import com.zscat.mallplus.sys.entity.SysRole;
import com.zscat.mallplus.sys.service.ISysRoleService;
import com.zscat.mallplus.utils.CommonResult;
import com.zscat.mallplus.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 专题商品关系表 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@Slf4j
@Controller
@RequestMapping("/cms/cmsSubjectProductRelation")
    public class CmsSubjectProductRelationController {

@Resource
private ISysRoleService sysRoleService;

@SysLog(MODULE = "sys", REMARK = "根据条件查询所有专题商品关系表列表")
@ApiOperation("根据条件查询所有专题商品关系表列表")
@GetMapping(value = "/list")
@PreAuthorize("hasAuthority('sys:role:read')")
public Object getRoleByPage(SysRole entity,
@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
        ){
        try{
        return new CommonResult().success(sysRoleService.page(new Page<SysRole>(pageNum,pageSize),new QueryWrapper<>(entity)));
        }catch(Exception e){
        log.error("根据条件查询所有专题商品关系表列表：%s",e.getMessage(),e);
        }
        return new CommonResult().failed();
        }
@SysLog(MODULE = "sys", REMARK = "保存专题商品关系表")
@ApiOperation("保存专题商品关系表")
@PostMapping(value = "/save")
@PreAuthorize("hasAuthority('sys:role:create')")
public Object saveRole(@RequestBody SysRole entity){
        try{
        entity.setId(IdWorker.getId());
        if(sysRoleService.save(entity)){
        return new CommonResult().success();
        }
        }catch(Exception e){
        log.error("保存专题商品关系表：%s",e.getMessage(),e);
        return new CommonResult().failed();
        }
        return new CommonResult().failed();
        }
@SysLog(MODULE = "sys", REMARK = "更新专题商品关系表")
@ApiOperation("更新专题商品关系表")
@PutMapping(value = "/update/{id}")
@PreAuthorize("hasAuthority('sys:role:update')")
public Object updateRole(@RequestBody SysRole entity){
        try{
        if(sysRoleService.updateById(entity)){
        return new CommonResult().success();
        }
        }catch(Exception e){
        log.error("更新专题商品关系表：%s",e.getMessage(),e);
        return new CommonResult().failed();
        }
        return new CommonResult().failed();
        }
@SysLog(MODULE = "sys", REMARK = "删除专题商品关系表")
@ApiOperation("删除专题商品关系表")
@DeleteMapping(value = "/delete/{id}")
@PreAuthorize("hasAuthority('sys:role:delete')")
public Object deleteRole(@ApiParam("专题商品关系表id") @PathVariable Long id){
        try{
        if(ValidatorUtils.empty(id)){
        return new CommonResult().paramFailed("专题商品关系表id");
        }
        if(sysRoleService.removeById(id)){
        return new CommonResult().success();
        }
        }catch(Exception e){
        log.error("删除专题商品关系表：%s",e.getMessage(),e);
        return new CommonResult().failed();
        }
        return new CommonResult().failed();
        }
@SysLog(MODULE = "sys", REMARK = "给专题商品关系表分配专题商品关系表")
@ApiOperation("查询专题商品关系表明细")
@GetMapping(value = "/{id}")
@PreAuthorize("hasAuthority('sys:role:read')")
public Object getRoleById(@ApiParam("专题商品关系表id") @PathVariable Long id){
        try{
        if(ValidatorUtils.empty(id)){
        return new CommonResult().paramFailed("专题商品关系表id");
        }
        SysRole coupon=sysRoleService.getById(id);
        return new CommonResult().success(coupon);
        }catch(Exception e){
        log.error("查询专题商品关系表明细：%s",e.getMessage(),e);
        return new CommonResult().failed();
        }

        }
@ApiOperation(value = "批量删除专题商品关系表")
@RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
@ResponseBody
@SysLog(MODULE = "pms", REMARK = "批量删除专题商品关系表")
@PreAuthorize("hasAuthority('sys:role:delete')")
public Object deleteBatch(@RequestParam("ids") List<Long> ids){
        boolean count=sysRoleService.removeByIds(ids);
        if(count){
        return new CommonResult().success(count);
        }else{
        return new CommonResult().failed();
        }
        }
        }

