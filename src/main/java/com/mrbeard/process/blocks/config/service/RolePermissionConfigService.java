package com.mrbeard.process.blocks.config.service;

import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.config.dto.PostRoleDto;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

/**
 * @InterFaceName RolePermissionConfigService
 * @Description 角色权限配置service
 * @Author Mrbeard
 * @Date 2019/1/11 15:38
 * @Version 1.0
 **/
public interface RolePermissionConfigService {

    /**
     * 获取角色列表
     * @return
     */
    Result getRoleList(Integer pageNum,Integer pageSize) throws ProcessRuntimeException;

    /**
     * 获取权限列表，如果rid不传则查全部
     * @param rid
     * @return
     */
    Result getPermissionList(String rid) throws ProcessRuntimeException;

    /**
     * 获取所有权限，如果Role有该权限的话添加标志位
     * @param rid
     * @return
     */
    Result getRolePermission(String rid) throws ProcessRuntimeException;


    /**
     * 配置角色权限
     * @param roleId 角色id
     * @throws ProcessRuntimeException
     * @param permissionIds 权限列表
     * @return
     */
    Result postRolePermissions(String roleId, String[] permissionIds) throws ProcessRuntimeException;

    /**
     * 配置角色
     * @param role 角色信息
     * @throws ProcessRuntimeException
     * @return
     */
    Result postRole(PostRoleDto role) throws ProcessRuntimeException;

    /**
     * 配置权限
     * @param permission 权限信息
     * @throws ProcessRuntimeException
     * @return
     */
    Result postPermission(Permission permission) throws ProcessRuntimeException;

    /**
     * 获取对应角色信息
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    Result getRole(String rid) throws ProcessRuntimeException;

    /**
     * 获取权限分页列表
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ProcessRuntimeException
     */
    Result getPermissionPage(Integer pageNum, Integer pageSize) throws ProcessRuntimeException;

    /**
     * 获取权限信息
     * @param pid
     * @return
     * @throws ProcessRuntimeException
     */
    Result getPermission(String pid) throws ProcessRuntimeException;
}
