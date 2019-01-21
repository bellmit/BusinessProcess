package com.mrbeard.process.blocks.config.service.impl;

import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.blocks.config.service.RolePermissionConfigService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public Result getRoleList() throws ProcessRuntimeException {
        //判断是否有权限
        Set<String> permissions = SessionUtil.getPermissions();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        if (permissions.contains(methodName) != true) {
            return ResultGenerator.getErrorResult("权限不足！请联系管理员");
        }
        List<Role> roleList = roleService.getRoleList();
        return ResultGenerator.getSuccessResult(roleList);
    }
}
