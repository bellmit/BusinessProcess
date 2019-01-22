package com.mrbeard.process.blocks.config.controller;


import com.mrbeard.process.blocks.config.service.RolePermissionConfigService;
import com.mrbeard.process.blocks.publish.model.Comment;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

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
    public Result getRoleList(Integer pageNum,Integer pageSize) throws ProcessRuntimeException {
        if(ToolUtil.checkParamter(pageNum,pageSize) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return rolePermissionConfigService.getRoleList(pageNum,pageSize);
    }


    @RequestMapping(value = "/getPermissionList",method = RequestMethod.GET)
    public Result getPermissionList(String rid) throws ProcessRuntimeException {
        return rolePermissionConfigService.getPermissionList(rid);
    }
}
