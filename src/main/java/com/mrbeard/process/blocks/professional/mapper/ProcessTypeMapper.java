package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.ProcessType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProcessTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessType record);

    int insertSelective(ProcessType record);

    ProcessType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProcessType record);

    int updateByPrimaryKey(ProcessType record);
}