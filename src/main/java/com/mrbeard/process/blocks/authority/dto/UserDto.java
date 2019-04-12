package com.mrbeard.process.blocks.authority.dto;

import java.util.Date;

/**
 * @ClassName UserDto
 * @Description 用户列表显示dto
 * @Author Mrbeard
 * @Date 2019/4/12 19:47
 * @Version 1.0
 **/
public class UserDto {

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
     * 用户角色值Id
     */
    private String roleId;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 部门name
     */
    private String deptName;

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

    /**
     * 性别
     */
    private String sex;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
