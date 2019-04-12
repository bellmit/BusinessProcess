package com.mrbeard.process.blocks.config.service;

import com.mrbeard.process.blocks.authority.dto.DeptmentDto;
import com.mrbeard.process.blocks.authority.model.Department;

import java.util.List;

/**
 * @InterFaceName DeptService
 * @Description 部门service
 * @Author Mrbeard
 * @Date 2019/4/13 0:28
 * @Version 1.0
 **/
public interface DeptService {

    /**
     * 获取部门列表
     * @return
     */
    List<DeptmentDto> getDeptList();
}
