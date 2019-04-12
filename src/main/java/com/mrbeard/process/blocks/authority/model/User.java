package com.mrbeard.process.blocks.authority.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 胡彬
 * @date 2018-11-10
 */
public class User  implements Serializable {
    /**
     * 用户id
     */
    private String uid;
    /**
     * 用户名
     */
    private String uname;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 状态 1:启用 0：禁用
     */
    private Integer state;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 登录端口
     */
    private Integer loginPort;
    /**
     *
     * 用户角色值
     */
    private String role;
    /**
     * 用户所有权限值
     */
    private Set<String> permissions = new HashSet<>();

    /**
     * 部门id
     */
    private String deptid;

    /**
     * 上级id
     */
    private String parentsid;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    public User(String uid, String uname, Integer state, String nick, String password,
                String loginIp, Integer loginPort, String deptid,String parentsid,
                Date createdTime, Date updatedTime,String realName,String phoneNumber,String email) {
        this.uid = uid;
        this.uname = uname;
        this.nick = nick;
        this.password = password;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.state = state;
        this.loginIp = loginIp;
        this.loginPort = loginPort;
        this.deptid = deptid;
        this.parentsid = parentsid;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getLoginPort() {
        return loginPort;
    }

    public void setLoginPort(Integer loginPort) {
        this.loginPort = loginPort;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getParentsid() {
        return parentsid;
    }

    public void setParentsid(String parentsid) {
        this.parentsid = parentsid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}