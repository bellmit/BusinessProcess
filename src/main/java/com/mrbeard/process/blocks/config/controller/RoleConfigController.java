package com.mrbeard.process.blocks.config.controller;


import com.mrbeard.process.blocks.config.dto.PostRoleDto;
import com.mrbeard.process.blocks.config.service.RoleConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
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
public class RoleConfigController {

    @Autowired
    RoleConfigService roleConfigService;

    /**
     * 获取对应的角色信息
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @RequestMapping(value = "/getRole",method = RequestMethod.GET)
    public Result getRole(String rid) throws ProcessRuntimeException{
        if(ToolUtil.isEmpty(rid)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return roleConfigService.getRole(rid);
    }

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/getRoleList",method = RequestMethod.GET)
    public Result getRoleList(Integer pageNum,Integer pageSize,String rname) throws ProcessRuntimeException {
        if(ToolUtil.checkParamter(pageNum,pageSize) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return roleConfigService.getRoleList(pageNum,pageSize,rname);
    }


    /**
     * 配置角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/postRole",method = RequestMethod.POST)
    public Result postRole(PostRoleDto role) throws ProcessRuntimeException {
        return roleConfigService.postRole(role);
    }


}
