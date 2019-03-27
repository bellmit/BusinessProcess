package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.ProcessNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessNodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessNode record);

    int insertSelective(ProcessNode record);

    ProcessNode selectByPrimaryKey(String id);

    ProcessNode selectByCondition(ProcessNode processNode);

    int updateByPrimaryKeySelective(ProcessNode record);

    int updateByPrimaryKey(ProcessNode record);

    /**
     * 批量插入
     * @param nodeList
     */
    int insertBatch(List<ProcessNode> nodeList);
}