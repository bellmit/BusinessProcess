package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.ProcessNodeTypeBase;

import java.util.List;

public interface ProcessNodeTypeBaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessNodeTypeBase record);

    int insertSelective(ProcessNodeTypeBase record);

    ProcessNodeTypeBase selectByPrimaryKey(String id);

    /**
     * 通过id获取负责人
     * @param id
     * @return
     */
    List<ProcessNodeTypeBase> selectPersonIds(String id);

    int updateByPrimaryKeySelective(ProcessNodeTypeBase record);

    int updateByPrimaryKey(ProcessNodeTypeBase record);

    /**
     * 模糊搜索pareantsid
     * @param id
     * @return
     */
    List<ProcessNodeTypeBase> selectByParentsId(String id);
}