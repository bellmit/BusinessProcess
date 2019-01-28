package com.mrbeard.process.blocks.config.service.impl;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.model.RolePermission;
import com.mrbeard.process.blocks.authority.service.PermissionService;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.blocks.config.dto.PermissionDto;
import com.mrbeard.process.blocks.config.service.RolePermissionConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RolePermissionConfigServiceImpl
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/1/11 15:40
 * @Version 1.0
 **/
@Service
public class RolePermissionConfigServiceImpl implements RolePermissionConfigService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public Result getRoleList(Integer pageNum,Integer pageSize) throws ProcessRuntimeException {
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roleList = roleService.getRoleList();
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return ResultGenerator.getSuccessResult(pageInfo);
    }

    /**
     * 获取权限列表
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result getPermissionList(String rid) throws ProcessRuntimeException {
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        List<Permission> permissionList = roleService.getPermissionList(rid);
        return ResultGenerator.getSuccessResult(permissionList);
    }

    /**
     * 获取所有权限，如果Role有该权限的话添加标志位
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result getRolePermission(String rid) throws ProcessRuntimeException{
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        List<PermissionDto> permissionDtos = roleService.getRolePermission(rid);
        return ResultGenerator.getSuccessResult(permissionDtos);
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

}
