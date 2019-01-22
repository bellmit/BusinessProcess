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
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
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
     * 配置角色权限列表
     * @param roleId        角色id
     * @param permissionIds 权限列表
     * @return
     */
    @Override
    public Result postRolePermissions(String roleId, String[] permissionIds) throws ProcessRuntimeException {
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        //判断操作类型：新增、修改、删除
        //新增
        List<RolePermission> userPermissionListInData = permissionService.selectRolePermissionByRoleId(roleId);
        //判断数据中是否有角色对应的权限配置信息
        if(userPermissionListInData != null && userPermissionListInData.size() > 0){
            //修改
            if(permissionIds != null && permissionIds.length > 0) {
                permissionService.updateRolePermissionByRoleId(roleId,permissionIds);
                return ResultGenerator.getErrorResult("修改成功！");
            }
            //删除
            permissionService.deleteRolePermissionByRoleId(roleId);
            return ResultGenerator.getSuccessResult("删除角色权限信息成功！");
        }
        //新增
        if(permissionIds == null || permissionIds.length <= 0) {
            return ResultGenerator.getErrorResult("请至少选择一种权限！");
        }
        //设置数据
        List<RolePermission>  rolePermissionList = new ArrayList<>();
        for(int i = 0; i < permissionIds.length; i++){
            RolePermission rolePermission = new RolePermission(roleId,permissionIds[i]);
            rolePermissionList.add(rolePermission);
        }
        //插入
        permissionService.insertRolePermission(rolePermissionList);
        return ResultGenerator.getSuccessResult("新增成功！");
    }

    /**
     * 配置角色
     *
     * @param role 角色信息
     * @return
     */
    @Override
    public Result postRole(Role role) throws ProcessRuntimeException {
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        /**
         *
         * 判断操作类型：新增、删除、修改
         */
        if(ToolUtil.isNotEmpty(role.getRid())){
            //所有数据都不存在
            if(ToolUtil.checkIfAllParamtersExit(role.getRname(),role.getRvalue(),role.getRdescription()) != true){
                //删除
                roleService.deleteById(role.getRid());
                return ResultGenerator.getSuccessResult("删除成功！");
            }
            //修改
            if(ToolUtil.checkParamter(role.getRvalue(),role.getRname(),role.getRdescription()) != true){
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            Role roleInData = roleService.selectById(role.getRid());
            roleInData.setUpdated(new Date());
            roleInData.setRdescription(role.getRdescription());
            roleInData.setRname(role.getRname());
            roleInData.setRvalue(role.getRvalue());
            roleService.updateById(roleInData);
            return ResultGenerator.getSuccessResult("更新成功！");
        }
        //新增
        if(ToolUtil.checkParamter(role.getRname(),role.getRvalue(),role.getRdescription()) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        role.setRid(UUIDUtil.getUUID());
        role.setCreated(new Date());
        role.setUpdated(new Date());
        roleService.insert(role);
        return ResultGenerator.getSuccessResult("新增成功！");
    }

    /**
     * 配置权限
     *
     * @param permission 权限信息
     * @return
     */
    @Override
    public Result postPermission(Permission permission) throws ProcessRuntimeException {
        //判断是否有修改权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        //判断是什么操作：新增、删除、修改
        if(ToolUtil.isNotEmpty(permission.getPid())){
            //所有数据都不存在
            if(ToolUtil.checkIfAllParamtersExit(permission.getPname(),permission.getPvalue(),permission.getPtype()) != true){
                //删除
                permissionService.deleteById(permission.getPid());
                return ResultGenerator.getSuccessResult("删除成功！");
            }
            //修改
            if(ToolUtil.checkParamter(permission.getPname(),permission.getPvalue(),permission.getPtype()) != true){
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            //设置数据
            Permission permissionInData = permissionService.selectById(permission.getPid());
            permissionInData.setUpdated(new Date());
            permissionInData.setPname(permission.getPname());
            permissionInData.setPtype(permission.getPtype());
            permissionInData.setPvalue(permission.getPvalue());
            //更新
            permissionService.updateById(permissionInData);
            return  ResultGenerator.getSuccessResult("更新成功！");
        }
        //新增
        permission.setPid(UUIDUtil.getUUID());
        permission.setUpdated(new Date());
        permission.setCreated(new Date());
        //新增
        permissionService.insert(permission);
        //返回结果
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
        //判断是否有修改权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
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
            userInData.setUpdated(new Date());
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
            user.setCreated(new Date());
            user.setUpdated(new Date());
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
        //判断是否有修改权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
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
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        User user =  userService.getUser(uid);
        return ResultGenerator.getSuccessResult(user);
    }


}
