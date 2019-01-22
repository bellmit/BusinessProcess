package com.mrbeard.process.blocks.authority.service;


import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.model.UserRole;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

import java.util.List;


/**
 * @author 胡彬
 */
public interface RoleService {

    /**
     * 根据用户id查询返回用户的所有角色
     * @throws ProcessRuntimeException
     * @param uid
     * @return
     */
     String getRoleByUserId(String uid) throws ProcessRuntimeException;

    /**
     * 插入用户角色关系
     * @param userRole
     * @throws ProcessRuntimeException
     * @return
     */
     int insertUserRole(UserRole userRole) throws ProcessRuntimeException;

    /**
     * 新增角色
     * @param role
     * @throws ProcessRuntimeException
     * @return
     */
     void insert(Role role) throws ProcessRuntimeException;

    /**
     * 删除一条记录
     * @param rid
     * @throws ProcessRuntimeException
     */
     void deleteById(String rid) throws ProcessRuntimeException;

    /**
     * 获取一条记录
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
     Role selectById(String rid) throws ProcessRuntimeException;

    /**
     * 更新记录
     * @throws ProcessRuntimeException
     * @param roleInData
     */
     void updateById(Role roleInData) throws ProcessRuntimeException;

    /**
     * 查找用户角色关系表
     * @param userId
     * @throws ProcessRuntimeException
     * @return
     */
    UserRole selectUserRoleByUserId(String userId) throws ProcessRuntimeException;

    /**
     * 更新用户角色信息
     * @param userId
     * @param roleId
     * @throws ProcessRuntimeException
     */
    void updateUserRoleByUserId(String userId, String roleId) throws ProcessRuntimeException;

    /**
     * 删除用户关联角色
     * @param userId
     * @throws ProcessRuntimeException
     */
    void deleteUserRoleByUserId(String userId) throws ProcessRuntimeException;

    /**
     * 获取角色列表
     * @return
     * @throws ProcessRuntimeException
     */
    List<Role> getRoleList() throws ProcessRuntimeException;

    /**
     * 获取权限列表
     * @param rid
     * @return
     */
    List<Permission> getPermissionList(String rid);
}
