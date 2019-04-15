package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.ProcessTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessTemplateMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessTemplate record);

    int insertSelective(ProcessTemplate record);

    ProcessTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProcessTemplate record);

    int updateByPrimaryKey(ProcessTemplate record);

    List<ProcessTemplate> selectByTypeId(String typeId);
}