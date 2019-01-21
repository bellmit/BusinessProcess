package com.mrbeard.process.blocks.userinfo.service;

import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @InterFaceName UserInfoService
 * @Description TODO
 * @Author Mrbeard
 * @Date 2018/12/15 19:44
 * @Version 1.0
 **/
public interface UserInfoService {

    /**
     * 上传头像
     * @param multipartFile
     * @return
     */
    Result postUserPhoto(MultipartFile multipartFile) throws ProcessRuntimeException;

    /**
     * 修改个人信息
     * @param user
     * @return
     * @throws ProcessRuntimeException
     */
    Result postUserInfo(User user) throws ProcessRuntimeException;
}
