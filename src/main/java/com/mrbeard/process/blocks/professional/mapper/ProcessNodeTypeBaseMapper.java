package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.blocks.professional.model.ProcessNodeTypeBase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessNodeTypeBaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessNodeTypeBase record);

    int insertSelective(ProcessNodeTypeBase record);

    ProcessNodeTypeBase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProcessNodeTypeBase record);

    int updateByPrimaryKey(ProcessNodeTypeBase record);

    /**
     * 模糊搜索pareantsid
     * @param id
     * @return
     */
    List<ProcessNodeTypeBase> selectByParentsId(String id);

    /**
     * 多项条件查询
     * @param processNodeTypeBase
     * @return
     */
    ProcessNodeTypeBase selectByCondition(ProcessNodeTypeDto processNodeTypeBase);

    /**
     * 多项条件查询列表
     * @param object
     * @return
     */
    List<ProcessNodeTypeBase> selectListByCondition(Object object);

    /**
     * 多项条件查询列表
     * @param object
     * @return
     */
    List<ProcessNodeTypeBase> selectListByConditionList(Object object);

}