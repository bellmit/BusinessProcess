package com.mrbeard.process.blocks.authority.service.impl;

import com.mrbeard.process.blocks.authority.mapper.RoleMapper;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.model.UserRole;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author 胡彬
 * @Date 2018/10/31 16:31
 **/
@Service
public class RoleServiceImpl  implements RoleService {

    @Resource
    RoleMapper roleDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取用户角色
     * @param uid
     * @return
     */
    @Override
    public String getRoleByUserId(String uid) {
        try {
            return roleDao.getRoleByUserId(uid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 插入用户角色关联信息
     * @param userRole
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public int insertUserRole(UserRole userRole) throws ProcessRuntimeException {
        try {
            return roleDao.insertUserRole(userRole);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 新增角色
     * @param role
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public void insert(Role role) throws ProcessRuntimeException {
        try {
            roleDao.insert(role);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 删除一条角色记录
     * @param rid
     * @throws ProcessRuntimeException
     */
    @Override
    public void deleteById(String rid) throws ProcessRuntimeException {
        try {
            roleDao.deleteById(rid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 查找角色记录
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Role selectById(String rid) throws ProcessRuntimeException {
        try {
            return roleDao.selectById(rid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 更新角色
     * @param roleInData
     * @throws ProcessRuntimeException
     */
    @Override
    public void updateById(Role roleInData) throws ProcessRuntimeException {
        try {
            roleDao.updateById(roleInData);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw  new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 查找用户关系表
     * @param userId
     * @return
     */
    @Override
    public UserRole selectUserRoleByUserId(String userId) throws ProcessRuntimeException {
        try {
            return roleDao.selectUserRoleByUserId(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 更新用户角色信息
     * @param userId
     * @param roleId
     * @throws ProcessRuntimeException
     */
    @Override
    public void updateUserRoleByUserId(String userId, String roleId) throws ProcessRuntimeException {
        try {
            UserRole userRole = new UserRole(userId,roleId,new Date(),new Date());
            roleDao.updateUserRole(userRole);
            //刷新redis
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 删除用户角色
     * @param userId
     * @throws ProcessRuntimeException
     */
    @Override
    public void deleteUserRoleByUserId(String userId) throws ProcessRuntimeException {
        try {
            roleDao.deleteUserRoleByUserId(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 获取角色列表
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public List<Role> getRoleList(Role role) throws ProcessRuntimeException {
        try {
            return roleDao.getRoleList(role);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }



    /**
     * 根据id获取角色信息
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Role getRole(String rid) throws ProcessRuntimeException {
        try {
            return roleDao.selectById(rid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }
}
