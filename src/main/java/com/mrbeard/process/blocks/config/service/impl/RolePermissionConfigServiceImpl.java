package com.mrbeard.process.blocks.config.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.model.Permission;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.blocks.config.service.RolePermissionConfigService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    RoleService roleService;

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
}
