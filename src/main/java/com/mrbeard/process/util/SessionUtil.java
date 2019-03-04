package com.mrbeard.process.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.page.controller.PageController;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author 胡彬
 * @date 2018-10-15 16:57
 * @description
 */
@Component
public class SessionUtil {

    private static Logger logger = LoggerFactory.getLogger(SessionUtil.class);

    /**
     * 根据usertoken获取redis中的用户session信息
     * @return
     */
    public static User getUserInfo() throws ProcessRuntimeException {
        //从参数中获取usertoken
        String usertoken = WebUtil.getRequest().getParameter("usertoken");
        User user = null;
        try {
            if(ToolUtil.isEmpty(usertoken)){
                WebUtil.getResponse().sendRedirect(WebUtil.getRequest().getContextPath() + "/sessionError");
            }
            //根据usertoken取session信息
            String userInfo = JedisUtil.get("usertoken_"+usertoken);
            //判断是否有模拟用户
            if(ToolUtil.isEmpty(userInfo)){
                userInfo = JedisUtil.get("usertoken_mockUser");
                if(userInfo == null || "".equals(userInfo)){
                    return user;
                }
            }
            Map<String, Object> userInfoMap = JSON.parseObject(userInfo, new TypeReference<Map<String, Object>>() {});
            user = JSON.parseObject(String.valueOf(userInfoMap.get("userInfo")), User.class);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
        return user;
    }


    /**
     * 获取权限
     * @return
     */
    public static Set<String> getPermissions(){
        //获取账户
        User userInfo = getUserInfo();
        //获取权限列表
        Set<String> permissions = userInfo.getPermissions();
        //返回
        return permissions;
    }
}
