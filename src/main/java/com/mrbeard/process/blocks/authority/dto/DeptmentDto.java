package com.mrbeard.process.blocks.authority.dto;

import java.util.Date;

/**
 * @ClassName DeptmentDto
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/4/13 0:36
 * @Version 1.0
 **/
public class DeptmentDto {
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 部门name
     */
    private String deptName;
    /**
     * 上级部门id
     */
    private String parentsId;
    /**
     * 上级部门name
     */
    private String parentsName;
    /**
     * 部门主管id
     */
    private String leaderId;
    /**
     * 部门主管name
     */
    private String leaderName;
    /**
     * 部门状态 0-禁用 1-启用
     */
    private Byte state;
    private Date createdtime;
    private Date updatedtime;

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

    public String getParentsId() {
        return parentsId;
    }

    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(Date updatedtime) {
        this.updatedtime = updatedtime;
    }
}
