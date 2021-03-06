package com.mrbeard.process.blocks.config.mapper;

import com.mrbeard.process.blocks.authority.dto.UserDto;
import com.mrbeard.process.blocks.config.model.User;
import com.mrbeard.process.blocks.professional.model.Process;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 胡彬
 */
@Mapper
public interface UserMapper {
    /**
     * delete
     * @param uid
     * @return
     */
    int deleteByPrimaryKey(String uid);


    /**
     * insert
     * @param record
     * @return
     */
    int insert(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKey(User record);

    /**
     * 通过用户名查找用户
     * @param uname
     * @return
     */
    List<User> findUserByName(String uname);

    /**
     * 通过条件查找用户列表
     * @param user
     * @return
     */
    List<UserDto> selectUserList(User user);

    /**
     * 通过id集合查找
     * @param processes
     * @return
     */
    List<User> selectListByIds(List<Process> processes);
}