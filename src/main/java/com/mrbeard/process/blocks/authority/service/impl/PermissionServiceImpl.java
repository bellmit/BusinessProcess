package com.mrbeard.process.blocks.authority.service.impl;

import com.mrbeard.process.blocks.authority.mapper.PermissionMapper;
import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.RolePermission;
import com.mrbeard.process.blocks.authority.service.PermissionService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author 胡彬
 * @Date 2018/10/31 17:03
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionMapper permissionDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 通过用户id获取权限
     * @param uid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Set<String> getPermsByUserId(String uid) throws ProcessRuntimeException {
        try {
            return permissionDao.getPermsByUserId(uid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 删除
     * @param pid
     * @throws ProcessRuntimeException
     */
    @Override
    public void deleteById(String pid) throws ProcessRuntimeException {
        try {
            permissionDao.deleteByPrimaryKey(pid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 查找一条
     * @param pid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Permission selectById(String pid) throws ProcessRuntimeException {
        try {
            return permissionDao.selectByPrimaryKey(pid);
        } catch (ProcessRuntimeException e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 更新
     * @param permissionInData
     * @throws ProcessRuntimeException
     */
    @Override
    public void updateById(Permission permissionInData) throws ProcessRuntimeException {
        try {
            permissionDao.updateByPrimaryKey(permissionInData);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 新增一条
     * @param permission
     * @throws ProcessRuntimeException
     */
    @Override
    public void insert(Permission permission) throws ProcessRuntimeException {
        try {
            permissionDao.insert(permission);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 新增角色权限关联信息
     * @param rolePermissionList
     * @throws ProcessRuntimeException
     */
    @Override
    public void insertRolePermission(List<RolePermission> rolePermissionList) throws ProcessRuntimeException {
        try {
            permissionDao.insertRolePermission(rolePermissionList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 更新角色权限
     * @param roleId
     * @param permissionIds
     * @throws ProcessRuntimeException
     */
    @Override
    public void updateRolePermissionByRoleId(String roleId, String[] permissionIds) throws ProcessRuntimeException {
        try {
            permissionDao.deleteRolePermissionByRoleId(roleId);
            List<RolePermission> rolePermissionList = new ArrayList<>();
            for(int i = 0; i < permissionIds.length; i++){
                RolePermission rolePermission = new RolePermission(roleId,permissionIds[i]);
                rolePermissionList.add(rolePermission);
            }
            permissionDao.insertRolePermission(rolePermissionList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }
    /**
     * 删除角色权限
     * @param roleId
     * @throws ProcessRuntimeException
     */
    @Override
    public void deleteRolePermissionByRoleId(String roleId) throws ProcessRuntimeException {
        try {
            permissionDao.deleteRolePermissionByRoleId(roleId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }
    /**
     * 通过角色id查找角色权限信息
     * @param roleId
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public List<RolePermission> selectRolePermissionByRoleId(String roleId) throws ProcessRuntimeException {
        try {
            return permissionDao.selectRolePermissionByRoleId(roleId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }
}
