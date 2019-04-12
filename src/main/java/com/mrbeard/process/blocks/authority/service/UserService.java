package com.mrbeard.process.blocks.authority.service;


import com.mrbeard.process.blocks.authority.dto.UserDto;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.exception.ProcessRuntimeException;

import java.util.List;


/**
 * @author 胡彬
 * @date 2018/11/10
 */
public interface UserService {

    /**
     * 根据用户名查询一条数据
     * @param uname
     * @return
     * @throws ProcessRuntimeException
     */
     User selectUserByName(String uname) throws ProcessRuntimeException;

    /**
     * 通过id查找用户
     * @param uid
     * @return
     * @throws ProcessRuntimeException
     */
     User selectUserById(String uid) throws ProcessRuntimeException;

    /**
     * 插入一条数据
     * @param record
     * @return
     * @throws ProcessRuntimeException
     */
    int insert(User record) throws ProcessRuntimeException;

    /**
     * 根据id删除用户
     * @param userId
     * @return
     * @throws ProcessRuntimeException
     */
    int deleteById(String userId) throws ProcessRuntimeException;

    /**
     * 更新用户
     * @param user
     * @throws ProcessRuntimeException
     */
    void updateById(User user) throws ProcessRuntimeException;

    /**
     * 根据条件查找用户列表
     * @param user
     * @return
     */
    List<UserDto> selectUserList(User user) throws ProcessRuntimeException;

    /**
     * 通过id获取用户信息
     * @param uid
     * @return
     */
    User getUser(String uid) throws ProcessRuntimeException;
}
