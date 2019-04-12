package com.mrbeard.process.blocks.config.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.model.*;
import com.mrbeard.process.blocks.authority.service.PermissionService;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.blocks.config.service.UserConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserConfigServiceImpl
 * @Description 用户信息配置
 * @Author Mrbeard
 * @Date 2018/12/14 16:39
 * @Version 1.0
 **/
@Service
public class UserConfigServiceImpl implements UserConfigService {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;


    /**
     * 配置用户角色
     *
     * @param userId 用户id
     * @param roleId 角色列表
     * @return
     */
    @Override
    public Result postUserRole(String userId, String roleId)  throws ProcessRuntimeException {
        //判断操作类型：新增、修改、删除
        //新增
        UserRole userRoleInData = roleService.selectUserRoleByUserId(userId);
        //判断数据中是否有用户对应的角色配置信息
        if(userRoleInData != null){
            //修改
            if(roleId != null) {
                roleService.updateUserRoleByUserId(userId, roleId);
                return ResultGenerator.getErrorResult("修改成功！");
            }
            //删除
            roleService.deleteUserRoleByUserId(userId);
            return ResultGenerator.getSuccessResult("删除用户角色信息成功！");
        }
        //新增
        if(roleId == null) {
            return ResultGenerator.getErrorResult("请选择角色！");
        }
        //设置数据
        UserRole userRole = new UserRole(userId,roleId);
        //插入
        roleService.insertUserRole(userRole);
        return ResultGenerator.getSuccessResult("新增成功！");
    }


    /**
     * 配置用户
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public Result postUser(User user) throws ProcessRuntimeException {
        /**
         * 判断当前操作：增加、修改、删除
         */
        if (ToolUtil.isNotEmpty(user.getUid())) {
            //删除
            boolean flag = ToolUtil.checkParamter(user.getUname(), user.getPassword(), user.getState());
            if (flag != true) {
                //删除对应角色信息
                roleService.deleteUserRoleByUserId(user.getUid());
                //删除用户信息
                userService.deleteById(user.getUid());
                return ResultGenerator.getSuccessResult("删除成功！");
            }
            //修改
            //设置数据
            User userInData = userService.selectUserById(user.getUid());
            userInData.setUpdatedTime(new Date());
            userInData.setUname(user.getUname());
            userInData.setPassword(ToolUtil.Md5(user.getPassword()));
            userInData.setState(user.getState());
            userInData.setNick(user.getNick());
            //更新
            userService.updateById(userInData);
            //更新角色用户信息
            roleService.updateUserRoleByUserId(user.getUid(),user.getRole());
            return ResultGenerator.getSuccessResult("修改成功！");
        } else {
            //新增
            //参数判断
            if (ToolUtil.checkParamter(user.getUname(), user.getPassword(),user.getState(),user.getRole()) != true) {
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            //校验数据库中是否有一样的用户名
            User userByName = userService.selectUserByName(user.getUname());
            if(userByName != null ){
                return ResultGenerator.getErrorResult("该用户名已被注册!");
            }
            user.setUid(UUIDUtil.getUUID());
            user.setCreatedTime(new Date());
            user.setUpdatedTime(new Date());
            user.setPassword(ToolUtil.Md5(user.getPassword()));
            userService.insert(user);
            //新增用户角色信息
            UserRole userRole = new UserRole(user.getUid(),user.getRole());
            roleService.insertUserRole(userRole);
            return ResultGenerator.getSuccessResult("新增用户信息成功！");
        }
    }

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    @Override
    public Result getUserList(Integer pageNum, Integer pageSize, User user) throws ProcessRuntimeException {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userService.selectUserList(user);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return ResultGenerator.getSuccessResult(pageInfo);
    }

    /**
     * 通过用户id获取用户信息
     * @param uid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result getUser(String uid) throws ProcessRuntimeException {
        User user =  userService.getUser(uid);
        return ResultGenerator.getSuccessResult(user);
    }


}
