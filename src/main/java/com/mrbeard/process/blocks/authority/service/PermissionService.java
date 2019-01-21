package com.mrbeard.process.blocks.authority.service;

import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.RolePermission;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 * @author 胡彬
 */
@Service
public interface PermissionService {

    /**
     * 根据用户id查询返回用户的所有权限，实际查询语句参考：
     * @param uid
     * @throws ProcessRuntimeException
     * @return
     */
    Set<String> getPermsByUserId(String uid) throws ProcessRuntimeException;

    /**
     * 删除一条数据
     * @throws ProcessRuntimeException
     * @param pid
     */
    void deleteById(String pid) throws ProcessRuntimeException;

    /**
     * 查找一条数据
     * @param pid
     * @throws ProcessRuntimeException
     * @return
     */
    Permission selectById(String pid) throws ProcessRuntimeException;

    /**
     * 更新数据
     * @param permissionInData
     * @throws ProcessRuntimeException
     */
    void updateById(Permission permissionInData) throws ProcessRuntimeException;

    /**
     * 新增一条
     * @param permission
     * @throws ProcessRuntimeException
     */
    void insert(Permission permission) throws ProcessRuntimeException;

    /**
     * 新增角色权限关联信息
     * @param rolePermissionList
     * @throws ProcessRuntimeException
     */
    void insertRolePermission(List<RolePermission> rolePermissionList) throws ProcessRuntimeException;

    /**
     * 更新角色权限
     * @param roleId
     * @param permissionIds
     * @throws ProcessRuntimeException
     */
    void updateRolePermissionByRoleId(String roleId, String[] permissionIds) throws ProcessRuntimeException;

    /**
     * 删除角色权限
     * @param roleId
     * @throws ProcessRuntimeException
     */
    void deleteRolePermissionByRoleId(String roleId) throws ProcessRuntimeException;

    /**
     * 通过角色id查找角色权限信息
     * @param roleId
     * @return
     * @throws ProcessRuntimeException
     */
    List<RolePermission> selectRolePermissionByRoleId(String roleId) throws ProcessRuntimeException;
}
