package com.mrbeard.process.blocks.userinfo.controller;

import com.mrbeard.process.blocks.config.model.User;
import com.mrbeard.process.blocks.userinfo.service.UserInfoService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UserInfoController
 * @Description TODO
 * @Author Mrbeard
 * @Date 2018/12/15 19:57
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;
    /**
     * 修改头像
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/postUserPhoto",method = RequestMethod.POST)
    public Result postUserPhoto(@RequestParam("file") MultipartFile multipartFile) throws ProcessRuntimeException {
        //参数校验
        if (multipartFile == null) {
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return userInfoService.postUserPhoto(multipartFile);
    }

    /**
     *修改个人信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/postUserInfo",method = RequestMethod.POST)
    public Result postUserInfo(User user) throws ProcessRuntimeException {
        //参数校验
        if(ToolUtil.checkParamter(user.getUname(),user.getNick(),user.getPassword()) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return userInfoService.postUserInfo(user);
    }

}
