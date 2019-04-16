package com.mrbeard.process.blocks.authority.mapper;

import com.mrbeard.process.blocks.authority.dto.StationDto;
import com.mrbeard.process.blocks.authority.model.Station;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Station record);

    int insertSelective(Station record);

    Station selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);

    List<StationDto> selectByCondition(StationDto stationDto);
}