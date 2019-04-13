package com.mrbeard.process.blocks.authority.service;

import com.mrbeard.process.blocks.authority.dto.LoginDto;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

import java.io.IOException;

/**
 * @Author 胡彬
 * 权限相关service
 * @Date 2018/10/31 15:27
 **/
public interface AuthorityService {

    /**
     * 登录
     * @param loginDto
     * @return
     */
    Result login(LoginDto loginDto) throws ProcessRuntimeException;

    /**
     * 注销
     * @param usertoken
     * @return
     */
    Result logout(String usertoken) throws ProcessRuntimeException;

    /**
     * 根据browerToken获取验证码
     * @param browerToken
     * @throws IOException
     */
    void getRandomCode(String browerToken) throws IOException;

    /**
     * 是否过期
     * @return
     */
    Result isTokenTimeOut();
}
