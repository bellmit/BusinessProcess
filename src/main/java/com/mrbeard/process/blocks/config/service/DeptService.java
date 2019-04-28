package com.mrbeard.process.blocks.config.service;

import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.dto.DeptmentDto;

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


    /**
     * 获取部门列表，分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<DeptmentDto> getDeptListWithPage(Integer pageNum, Integer pageSize);

    /**
     * 删除
     * @param deptmentDto
     * @return
     */
    int deleteSelective(DeptmentDto deptmentDto);

    /**
     * 更新
     * @param deptmentDto
     * @return
     */
    int updateSelective(DeptmentDto deptmentDto);
}
