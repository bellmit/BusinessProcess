package com.mrbeard.process.blocks.config.controller;

import com.mrbeard.process.blocks.authority.dto.DeptmentDto;
import com.mrbeard.process.blocks.authority.dto.StationDto;
import com.mrbeard.process.blocks.authority.dto.UserDto;
import com.mrbeard.process.blocks.config.model.User;
import com.mrbeard.process.blocks.config.service.UserConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mrbeard
 * @date 2018-12-14
 * 用户信息配置
 */
@RequestMapping("/api")
@RestController
public class UserConfigController {

    @Autowired
    UserConfigService userConfigService;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 配置用户
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/postUser",method = RequestMethod.POST)
    public Result postUser(UserDto userDto) throws ProcessRuntimeException {
        return userConfigService.postUser(userDto);
    }

    /**
     * 修改用户状态
     * @param uid
     * @param state
     * @return
     */
    @RequestMapping(value = "/updateUserState",method = RequestMethod.POST)
    public Result updateUserState(String uid,Integer state){
        if(ToolUtil.checkParamter(uid,state) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return userConfigService.updateUserState(uid,state);
    }

    /**
     * 配置用户角色
     *
     * @param userId 用户Id
     * @param roleId 角色Id
     * @return
     */
    @RequestMapping(value = "/postUserRole",method = RequestMethod.POST)
    public Result postUserRole(String userId, String roleId) throws ProcessRuntimeException {
        //参数校验
        if(ToolUtil.checkParamter(userId) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return userConfigService.postUserRole(userId, roleId);
    }


    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public Result getUserList(Integer pageNum,Integer pageSize,User user) throws ProcessRuntimeException {
        if(ToolUtil.checkParamter(pageNum,pageSize) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return userConfigService.getUserList(pageNum,pageSize,user);
    }

    /**
     * 通过id获取用户信息
     * @return
     * @param uid
     * @throws ProcessRuntimeException
     */
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public Result getUser(String uid) throws ProcessRuntimeException {
        if(ToolUtil.isEmpty(uid)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return userConfigService.getUser(uid);
    }

    /**
     * 获取部门列表
     * @return
     */
    @GetMapping("/getDeptList")
    public Result getDeptList(){
        return userConfigService.getDeptList();
    }

    /**
     * 获取部门列表
     * @return
     */
    @GetMapping("/getDeptListWithPage")
    public Result getDeptList(Integer pageNum, Integer pageSize,String name, String code){
        return userConfigService.getDeptListWithPage(pageNum,pageSize,name,code);
    }

    /**
     * 配置部门信息
     * @param deptmentDto
     * @return
     */
    @RequestMapping(value = "/postDept",method = RequestMethod.POST)
    public Result postDept(DeptmentDto deptmentDto){
        return userConfigService.postDept(deptmentDto);
    }

    /**
     * 重置用户密码
     * @param uid
     * @return
     */
    @RequestMapping(value = "/restPassword")
    public Result restPassword(String uid){
        if(ToolUtil.checkParamter(uid)!= true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return userConfigService.restPassword(uid);
    }

    /**
     * 配置岗位信息
     * @param stationDto
     * @return
     */
    @RequestMapping(value = "/postStation",method = RequestMethod.POST)
    public Result postStation(StationDto stationDto){
        return userConfigService.postStation(stationDto);
    }


    /**
     * 获取岗位列表
     * @param pageNum
     * @param pageSize
     * @param stationDto
     * @return
     */
    @GetMapping("/getStationList")
    public Result getStationList(Integer pageNum, Integer pageSize, StationDto stationDto){
        return userConfigService.getStationList(pageNum,pageSize,stationDto);
    }

}
