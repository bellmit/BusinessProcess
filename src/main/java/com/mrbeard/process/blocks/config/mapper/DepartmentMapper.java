package com.mrbeard.process.blocks.config.mapper;

import com.mrbeard.process.blocks.authority.dto.DeptmentDto;
import com.mrbeard.process.blocks.config.model.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<DeptmentDto> getDeptList();

    List<DeptmentDto> getDeptListWithPage();
}