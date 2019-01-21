package com.mrbeard.process.blocks.authority.mapper;

import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 胡彬
 */
@Mapper
public interface RoleMapper {
    /**
     * 插入用户与角色关联信息
     * @param userRole
     * @return
     */
    int insertUserRole(UserRole userRole);

    /**
     * 删除用户角色关联关系
     * @param uid
     */
    void deleteUserRoleByUserId(String uid);


    int updateUserRole(UserRole userRole);
    /**
     * 通过用户id获取用户角色
     * @param uid
     * @return
     */
    String getRoleByUserId(String uid);

    /**
     * 新增角色
     * @param role
     */
    void insert(Role role);

    /**
     * 删除一条记录
     * @param rid
     */
    void deleteById(String rid);

    /**
     * 获取一条记录
     * @param rid
     * @return
     */
    Role selectById(String rid);

    /**
     * 更新记录
     * @param role
     */
    void updateById(Role role);

    /**
     * 查找用户角色信息表
     * @param userId
     * @return
     */
    UserRole selectUserRoleByUserId(String userId);

    /**
     * 获取角色列表
     * @return
     */
    List<Role> getRoleList();
}