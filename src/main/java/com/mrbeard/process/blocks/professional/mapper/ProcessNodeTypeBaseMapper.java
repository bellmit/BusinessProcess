package com.mrbeard.process.blocks.professional.mapper;

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
    ProcessNodeTypeBase selectByCondition(ProcessNodeTypeBase processNodeTypeBase);

    /**
     * 多项条件查询列表
     * @param processNodeTypeBase
     * @return
     */
    List<ProcessNodeTypeBase> selectListByCondition(ProcessNodeTypeBase processNodeTypeBase);

    /**
     * 获取所有开始节点列表
     * @return
     */
    List<ProcessNodeTypeBase> selectBeginNodeList();
}