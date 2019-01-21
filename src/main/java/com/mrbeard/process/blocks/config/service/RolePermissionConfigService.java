package com.mrbeard.process.blocks.config.service;

import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

/**
 * @InterFaceName RolePermissionConfigService
 * @Description 角色权限配置service
 * @Author Mrbeard
 * @Date 2019/1/11 15:38
 * @Version 1.0
 **/
public interface RolePermissionConfigService {

    /**
     * 获取角色列表
     * @return
     */
    Result getRoleList() throws ProcessRuntimeException;
}
