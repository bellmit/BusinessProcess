package com.mrbeard.process.blocks.config.controller;


import com.mrbeard.process.blocks.config.service.RolePermissionConfigService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RolePermissionConfigController
 * @Description 角色权限管理controller
 * @Author Mrbeard
 * @Date 2019/1/11 15:34
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api")
public class RolePermissionConfigController {

    @Autowired
    RolePermissionConfigService rolePermissionConfigService;

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/getRoleList",method = RequestMethod.GET)
    public Result getRoleList() throws ProcessRuntimeException {
        return rolePermissionConfigService.getRoleList();
    }
}
