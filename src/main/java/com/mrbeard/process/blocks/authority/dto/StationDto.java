package com.mrbeard.process.blocks.authority.dto;

import java.util.Date;

/**
 * @ClassName StationDto
 * @Description 岗位dto
 * @Author Mrbeard
 * @Date 2019/4/16 15:44
 * @Version 1.0
 **/
public class StationDto {

    private String id;

    private String name;

    private String uid;

    private String uname;

    private String deptid;

    private String deptName;

    private Date createdtime;

    private Date updatedtime;

    public StationDto(String id, String name, String uid, String uname, String deptid, String deptName, Date createdtime, Date updatedtime) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.uname = uname;
        this.deptid = deptid;
        this.deptName = deptName;
        this.createdtime = createdtime;
        this.updatedtime = updatedtime;
    }

    public StationDto() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
