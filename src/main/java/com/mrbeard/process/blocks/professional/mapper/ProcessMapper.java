package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.Process;
import com.mrbeard.process.blocks.professional.model.ProcessNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mrbeard
 */
@Mapper
public interface ProcessMapper {
    int deleteByPrimaryKey(String id);

    int insert(Process record);

    int insertSelective(Process record);

    Process selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);

    List<Process> selectListByIds(List<ProcessNode> processNodes);
}