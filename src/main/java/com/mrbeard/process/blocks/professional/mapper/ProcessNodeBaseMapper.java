package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.ProcessNodeBase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProcessNodeBaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessNodeBase record);

    int insertSelective(ProcessNodeBase record);

    ProcessNodeBase selectByPrimaryKey(String id);

    ProcessNodeBase selectByNodeType(String nodeType);

    int updateByPrimaryKeySelective(ProcessNodeBase record);

    int updateByPrimaryKey(ProcessNodeBase record);
}