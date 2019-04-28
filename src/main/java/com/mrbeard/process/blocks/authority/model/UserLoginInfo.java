package com.mrbeard.process.blocks.authority.model;

import java.util.Date;

public class UserLoginInfo {
    private String uid;

    private String ip;

    private String isonline;

    private String uname;

    private Integer someThingToDo;

    private Date createdTime;

    private Date updatedTime;

    public UserLoginInfo(String uid, String ip, String isonline, String uname,
                         Integer someThingToDo, Date createdTime, Date updatedTime) {
        this.uid = uid;
        this.ip = ip;
        this.isonline = isonline;
        this.uname = uname;
        this.someThingToDo = someThingToDo;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public UserLoginInfo() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getIsonline() {
        return isonline;
    }

    public void setIsonline(String isonline) {
        this.isonline = isonline == null ? null : isonline.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public Integer getSomeThingToDo() {
        return someThingToDo;
    }

    public void setSomeThingToDo(Integer someThingToDo) {
        this.someThingToDo = someThingToDo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}