package com.mrbeard.process.common;


import com.mrbeard.process.blocks.authority.mapper.UserLoginInfoMapper;
import com.mrbeard.process.blocks.authority.model.UserLoginInfo;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @InterFaceName WebSocketService
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/1/31 11:09
 * @Version 1.0
 **/
@ServerEndpoint("/websocket/{uid}")
@Component
public class WebSocket {
    @Resource
    private UserLoginInfoMapper userLoginInfoDao;

     private static Logger log = LoggerFactory.getLogger(WebSocket.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static AtomicLong onlineCount = new AtomicLong(0);
    /**
     *concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收sid
     */
    private String uid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("uid") String uid) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        addOnlineCount();
        //用户信息存入库
        setUserInfoToData(uid);
        log.info("有新窗口开始监听:"+uid+",当前在线人数为" + getOnlineCount());
        this.uid=uid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 将用户存入信息表
     * @param uid
     */
    private void setUserInfoToData(String uid) {
        //将在线用户存入在线用户表
        //判断是否表里有数据
        int countById = userLoginInfoDao.selectCountById(uid);
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        if(countById <= 0){
            //新插入数据
            userLoginInfo.setUid(uid);
            userLoginInfo.setIsonline("1");
            userLoginInfo.setUname(SessionUtil.getUserInfo().getUname());
            userLoginInfo.setIp(WebUtil.getRequest().getRemoteAddr());
            userLoginInfoDao.insert(userLoginInfo);
        }else{
            userLoginInfo.setUid(uid);
            userLoginInfo.setIsonline("1");
            if(userLoginInfo.getSomeThingToDo() > 0){
                //通知
                try {
                    sendInfo("您有"+userLoginInfo.getSomeThingToDo()+"条消息需要处理！",uid);
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                    throw new ProcessRuntimeException(e.getMessage());
                }
            }
            userLoginInfo.setSomeThingToDo(0);
            userLoginInfoDao.updateByPrimaryKeySelective(userLoginInfo);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        //在线数减1
        subOnlineCount();
        //更新在线状态
        UserLoginInfo userLoginInfo = userLoginInfoDao.selectByPrimaryKey(uid);
        userLoginInfo.setIsonline("0");
        userLoginInfoDao.updateByPrimaryKeySelective(userLoginInfo);
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+uid+"的信息:"+message);
        //群发消息
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                log.error(e.getMessage(),e);
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("uid") String uid) throws IOException {
        log.info("推送消息到窗口"+uid+"，推送内容:"+message);
        for (WebSocket item : webSocketSet) {
            try {
                //这里可以设定只推送给这个uid的，为null则全部推送
                if(uid==null) {
                    item.sendMessage(message);
                }else if(item.uid.equals(uid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized AtomicLong getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount.getAndIncrement();
    }

    public static synchronized void subOnlineCount() {
        onlineCount.getAndDecrement();
    }
}
