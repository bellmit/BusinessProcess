package com.mrbeard.process.blocks.authority.mapper;

import com.mrbeard.process.blocks.authority.model.UserLoginInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginInfoMapper {
    int deleteByPrimaryKey(String uid);

    int insert(UserLoginInfo record);

    int insertSelective(UserLoginInfo record);

    int selectCountById(String uid);

    UserLoginInfo selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(UserLoginInfo record);

    int updateByPrimaryKey(UserLoginInfo record);
}