package com.mrbeard.process.blocks.authority.dto;

/**
 * @Author 胡彬
 * @Date 2018/11/27 10:27
 * 登录成功后返回给前端的封装dto
 **/
public class LoginResponseDto {
    /**
     * 用户唯一识别码
     */
    private String usertoken;
    /**
     * 用户名
     */
    private String uname;
    /**
     * 用户id
     */
    private String uid;
    /**
     * 用户昵称
     */
    private String nick;

    public LoginResponseDto(String usertoken, String uname, String uid, String nick) {
        this.usertoken = usertoken;
        this.uname = uname;
        this.uid = uid;
        this.nick = nick;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
