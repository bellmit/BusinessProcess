package com.mrbeard.process.blocks.professional.controller;

import com.mrbeard.process.common.WebSocket;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @ClassName TestWebSocket
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/1/31 14:26
 * @Version 1.0
 **/
public class TestWebSocket {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Result<String> tet(String uid, String message){
        try {
            WebSocket.sendInfo("success!!!","ffa20ac2933c4cc68ea51f08b9fe6277");
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return ResultGenerator.getErrorResult("推送失败：uid = "+uid+"错误信息: "+e.getMessage());
        }
        return ResultGenerator.getSuccessResult("推送消息成功! uid ="+uid);
    }
}
