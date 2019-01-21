package com.mrbeard.process.blocks.authority.controller;

import com.mrbeard.process.blocks.authority.dto.LoginDto;
import com.mrbeard.process.blocks.authority.service.AuthorityService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author 胡彬
 * @Date 2018/10/30 17:13
 * 用户登录注册注销
 **/
@RestController
@RequestMapping("/api")
public class AuthorityController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityController.class);


    @Autowired
    AuthorityService authorityService;

    /**
     * 登录接口
     * @param loginDto
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(LoginDto loginDto) throws ProcessRuntimeException{
        logger.info("====================/login=========================");
        if(ToolUtil.checkParamter(loginDto.getUsername(),loginDto.getPassword())!=true){
            return ResultGenerator.getErrorResult("用户名或密码为空");
        }
        return authorityService.login(loginDto);
    }

    /**
     * 注销接口
     * @return
     */
    @GetMapping("/logout")
    public Result logout(String usertoken) throws ProcessRuntimeException{
        logger.info("====================/logout=========================");
        return authorityService.logout(usertoken);
    }


    /**
     * 每个用户打开的浏览器通过这个接口获取一个识别码，后面生成验证码和校验验证码都需要根据这个识别码
     * @return
     */
    @GetMapping("/getBrowerToken")
    public Result getBrowerToken() throws ProcessRuntimeException{
        String browerToken = UUIDUtil.getUUID();
        //将系统识别码放入redis，有效时间600s,先放入固定值，后面生成验证码的时候，再将生成的随机码放入对应的key中
        JedisUtil.add("browerToken_"+browerToken,"undefinedBrowerToken", Constant.BORWER_TOKEN_INVALIDTIME);
        return ResultGenerator.getSuccessResult(browerToken);
    }

    /**
     * 获取验证码
     * @throws IOException
     */
    @GetMapping(value = "/getRandomCode")
    public void getRandomCode(String browerToken) throws Exception{
        authorityService.getRandomCode(browerToken);
    }



    /**
     * session校验失败，重定向接口
     * @return
     */
    @RequestMapping(value="/sessionError")
    public Result sessionError() throws ProcessRuntimeException{
        return ResultGenerator.getNotLoginResult();
    }




}
