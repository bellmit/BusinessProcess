package com.mrbeard.process.blocks.authority.service.impl;

import com.mrbeard.process.blocks.authority.dto.UserDto;
import com.mrbeard.process.blocks.config.mapper.UserMapper;
import com.mrbeard.process.blocks.config.model.User;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.blocks.professional.model.Process;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 胡彬
 * @Date 2018/10/31 16:21
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 通过uname查找用户
     * @param uname
     * @return
     */
    @Override
    public List<User> selectUserByName(String uname) throws ProcessRuntimeException {
        try {
            return userDao.findUserByName(uname);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 通过id查找用户
     * @param uid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public User selectUserById(String uid) throws ProcessRuntimeException {
        try {
            return userDao.selectByPrimaryKey(uid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public int insert(User record) throws ProcessRuntimeException {
        try {
            return userDao.insert(record);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 根据id删除用户
     * @param userId
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public int deleteById(String userId) throws ProcessRuntimeException {
        try {
            return userDao.deleteByPrimaryKey(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 根据id更新用户信息
     * @param user
     * @throws ProcessRuntimeException
     */
    @Override
    public void updateById(User user) throws ProcessRuntimeException {
        try {
            userDao.updateByPrimaryKey(user);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 根据条件查找用户列表
     * @param user
     * @return
     */
    @Override
    public List<UserDto> selectUserList(User user) {
        try {
            return userDao.selectUserList(user);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }


    /**
     * 根据id获取用户
     * @param uid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public User getUser(String uid) throws ProcessRuntimeException {
        try {
            return userDao.selectByPrimaryKey(uid);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }

    /**
     * 通过id集合查找
     * @param processes
     * @return
     */
    @Override
    public List<User> selectListByIds(List<Process> processes) {
        try {
            return userDao.selectListByIds(processes);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }


}
