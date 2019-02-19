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
    private Date created_time;
    /**
     * 更新时间
     */
    private Date updated_time;
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

    public User(String uid, String uname, Integer state, String nick, String password, String loginIp, Integer loginPort, Date created_time, Date updated_time,String deptid,String parentsid) {
        this.uid = uid;
        this.uname = uname;
        this.nick = nick;
        this.password = password;
        this.created_time = created_time;
        this.updated_time = updated_time;
        this.state = state;
        this.loginIp = loginIp;
        this.loginPort = loginPort;
        this.deptid = deptid;
        this.parentsid = parentsid;
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


    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
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
}