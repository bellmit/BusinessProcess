package com.mrbeard.process.blocks.config.service;


import com.mrbeard.process.blocks.authority.dto.DeptmentDto;
import com.mrbeard.process.blocks.authority.dto.StationDto;
import com.mrbeard.process.blocks.authority.dto.UserDto;
import com.mrbeard.process.blocks.config.model.User;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

/**
 * @InterFaceName UserConfigService
 * @Description 用户信息配置service
 * @Author Mrbeard
 * @Date 2018/12/14 16:29
 * @Version 1.0
 **/
public interface UserConfigService {


    /**
     * 配置用户角色
     * @param userId 用户id
     * @param roleId 角色
     * @throws ProcessRuntimeException
     * @return
     */
    Result postUserRole(String userId, String roleId) throws ProcessRuntimeException;



    /**
     * 配置用户信息
     * @param userDto
     * @return
     * @throws ProcessRuntimeException
     */
    Result postUser(UserDto userDto) throws ProcessRuntimeException;

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    Result getUserList(Integer pageNum, Integer pageSize, User user) throws ProcessRuntimeException;

    /**
     * 通过用户id获取用户信息
     * @param uid
     * @return
     * @throws ProcessRuntimeException
     */
    Result getUser(String uid) throws ProcessRuntimeException;

    /**
     * 获取部门列表
     * @return
     */
    Result getDeptList();

    /**
     * 重置密码
     * @param uid
     * @return
     */
    Result restPassword(String uid);

    /**
     * 修改用户状态
     * @param uid
     * @param state
     * @return
     */
    Result updateUserState(String uid, Integer state);

    /**
     * 配置岗位信息
     * @param stationDto
     * @return
     */
    Result postStation(StationDto stationDto);

    /**
     * 获取岗位列表
     * @param pageNum
     * @param pageSize
     * @param stationDto
     * @return
     */
    Result getStationList(Integer pageNum, Integer pageSize, StationDto stationDto);

    /**
     * 获取部门列表，带有分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result getDeptListWithPage(Integer pageNum, Integer pageSize,String name,String code);

    /**
     * 配置部门信息
     * @param deptmentDto
     * @return
     */
    Result postDept(DeptmentDto deptmentDto);
}
