package com.mrbeard.process.blocks.userinfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.blocks.userinfo.service.UserInfoService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.JedisUtil;
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserInfoServiceImpl
 * @Description TODO
 * @Author Mrbeard
 * @Date 2018/12/15 19:49
 * @Version 1.0
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserService userService;
    /**
     * 上传头像
     * @param multipartFile
     * @return
     */
    @Override
    public Result postUserPhoto(MultipartFile multipartFile) {
        //获取用户信息
        User userInfo = SessionUtil.getUserInfo();
        //判断是否有配置

        //设置文件路径
//        if()
        //保存图片
        //保存路径到对应的用户中
//        file.transferTo(new File("D:\\gitrep\\springboot\\testFile\\" + file.getOriginalFilename()));
        return null;
    }

    /**
     * 修改个人信息
     * @param user
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result postUserInfo(User user) throws ProcessRuntimeException {
        //获取当前用户
        String uid = SessionUtil.getUserInfo().getUid();
        User userInData = userService.selectUserById(uid);
        //设置数据
        userInData.setUpdated_time(new Date());
        userInData.setPassword(ToolUtil.Md5(user.getPassword()));
        userInData.setNick(user.getNick());
        userInData.setUname(user.getUname());
        //更新信息
        userService.updateById(userInData);
        //从request中获取到usertoken
        String usertoken = WebUtil.getRequest().getParameter("usertoken");
        //将用户信息放入userInfo
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("userInfo",userInData);
        //更新redis信息
        JedisUtil.add("usertoken_"+usertoken, JSON.toJSONString(userInfoMap),Constant.USER_TOKEN_INVALIDTIME);
        //返回信息
        return ResultGenerator.getSuccessResult("修改信息成功！");
    }
}
