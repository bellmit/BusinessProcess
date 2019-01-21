package com.mrbeard.process.blocks.authority.mapper;

import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 胡彬
 */
@Mapper
public interface PermissionMapper {
    /**
     * 删除一条权限
     * @param pid
     * @return
     */
    int deleteByPrimaryKey(String pid);

    /**
     * 新增一条权限
     * @param record
     * @return
     */
    int insert(Permission record);

    /**
     * 查找一条权限
     * @param pid
     * @return
     */
    Permission selectByPrimaryKey(String pid);

    /**
     * 更新权限
     * @param record
     * @return
     */
    int updateByPrimaryKey(Permission record);

    /**
     * 通过用户id查找所有权限
     * @param uid
     * @return
     */
    Set<String> getPermsByUserId(String uid);

    /**
     * 新增角色权限信息关联信息
     * @param rolePermissionList
     */
    void insertRolePermission(List<RolePermission> rolePermissionList);

    /**
     * 删除角色所有权限
     * @param rid
     */
    void deleteRolePermissionByRoleId(String rid);

    /**
     * 查找角色所有权限
     * @param rid
     * @return
     */
    List<RolePermission> selectRolePermissionByRoleId(String rid);
}