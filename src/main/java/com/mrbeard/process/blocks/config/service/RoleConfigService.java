package com.mrbeard.process.blocks.config.service;

import com.mrbeard.process.blocks.config.dto.PostRoleDto;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

/**
 * @InterFaceName RoleConfigService
 * @Description 角色权限配置service
 * @Author Mrbeard
 * @Date 2019/1/11 15:38
 * @Version 1.0
 **/
public interface RoleConfigService {

    /**
     * 获取角色列表
     * @return
     */
    Result getRoleList(Integer pageNum,Integer pageSize,String rname) throws ProcessRuntimeException;



    /**
     * 配置角色
     * @param role 角色信息
     * @throws ProcessRuntimeException
     * @return
     */
    Result postRole(PostRoleDto role) throws ProcessRuntimeException;



    /**
     * 获取对应角色信息
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    Result getRole(String rid) throws ProcessRuntimeException;



}
