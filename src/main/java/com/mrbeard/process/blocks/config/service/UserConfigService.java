package com.mrbeard.process.blocks.config.service;


import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

/**
 * @InterFaceName UserConfigService
 * @Description 用户信息配置service
 * @Author Mrbeard
 * @Date 2018/12/14 16:29
 * @Version 1.0
 **/
public interface UserConfigService {


    /**
     * 配置用户角色
     * @param userId 用户id
     * @param roleId 角色
     * @throws ProcessRuntimeException
     * @return
     */
    Result postUserRole(String userId, String roleId) throws ProcessRuntimeException;

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
    Result postRole(Role role) throws ProcessRuntimeException;

    /**
     * 配置权限
     * @param permission 权限信息
     * @throws ProcessRuntimeException
     * @return
     */
    Result postPermission(Permission permission) throws ProcessRuntimeException;

    /**
     * 配置用户信息
     * @param user
     * @return
     * @throws ProcessRuntimeException
     */
    Result postUser(User user) throws ProcessRuntimeException;

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    Result getUserList(Integer pageNum, Integer pageSize, User user) throws ProcessRuntimeException;

    /**
     * 通过用户id获取用户信息
     * @param uid
     * @return
     * @throws ProcessRuntimeException
     */
    Result getUser(String uid) throws ProcessRuntimeException;
}
