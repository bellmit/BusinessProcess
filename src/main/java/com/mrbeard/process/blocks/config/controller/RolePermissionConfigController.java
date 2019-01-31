package com.mrbeard.process.blocks.config.controller;


import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.config.dto.PostRoleDto;
import com.mrbeard.process.blocks.config.service.RolePermissionConfigService;
import com.mrbeard.process.blocks.config.service.UserConfigService;
import com.mrbeard.process.blocks.publish.model.Comment;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @ClassName RolePermissionConfigController
 * @Description 角色权限管理controller
 * @Author Mrbeard
 * @Date 2019/1/11 15:34
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api")
public class RolePermissionConfigController {

    @Autowired
    RolePermissionConfigService rolePermissionConfigService;

    /**
     * 获取对应的角色信息
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @RequestMapping(value = "/getRole",method = RequestMethod.GET)
    public Result getRole(String rid) throws ProcessRuntimeException{
        if(ToolUtil.isEmpty(rid)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return rolePermissionConfigService.getRole(rid);
    }

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/getRoleList",method = RequestMethod.GET)
    public Result getRoleList(Integer pageNum,Integer pageSize) throws ProcessRuntimeException {
        if(ToolUtil.checkParamter(pageNum,pageSize) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return rolePermissionConfigService.getRoleList(pageNum,pageSize);
    }


    /**
     * 获取权限分页列表
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ProcessRuntimeException
     */
    @RequestMapping(value = "/getPermissionPage",method = RequestMethod.GET)
    public Result getPermissionPage(Integer pageNum,Integer pageSize) throws ProcessRuntimeException{
        return rolePermissionConfigService.getPermissionPage(pageNum,pageSize);
    }

    /**
     * 获取所有权限（如果传rid,则查找该角色对应的权限）
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @RequestMapping(value = "/getPermissionList",method = RequestMethod.GET)
    public Result getPermissionList(String rid) throws ProcessRuntimeException {
        return rolePermissionConfigService.getPermissionList(rid);
    }


    /**
     * 获取所有权限，如果Role有该权限的话添加标志位
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @RequestMapping(value = "/getRolePermission",method = RequestMethod.GET)
    public Result getRolePermission(String rid) throws ProcessRuntimeException{
        if(ToolUtil.isEmpty(rid)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return rolePermissionConfigService.getRolePermission(rid);
    }


    /**
     * 配置角色权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    @RequestMapping(value = "/postRolePermissions",method = RequestMethod.POST)
    public Result postRolePermissions(String roleId, String [] permissionIds) throws ProcessRuntimeException {
        //参数校验
        if(ToolUtil.checkParamter(roleId,permissionIds) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return rolePermissionConfigService.postRolePermissions(roleId,permissionIds);
    }

    /**
     * 配置角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/postRole",method = RequestMethod.POST)
    public Result postRole(PostRoleDto role) throws ProcessRuntimeException {
        return rolePermissionConfigService.postRole(role);
    }

    /**
     * 配置权限
     * @param permission
     * @return
     */
    @RequestMapping(value = "/postPermission",method = RequestMethod.POST)
    public Result postPermission(Permission permission) throws ProcessRuntimeException {
        return rolePermissionConfigService.postPermission(permission);
    }

    /**
     * 获取权限信息
     * @param pid
     * @return
     * @throws ProcessRuntimeException
     */
    @RequestMapping(value = "/getPermission",method = RequestMethod.GET)
    public Result getPermission(String pid) throws ProcessRuntimeException{
        return rolePermissionConfigService.getPermission(pid);
    }
}
