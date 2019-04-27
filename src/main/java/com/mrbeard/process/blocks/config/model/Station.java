package com.mrbeard.process.blocks.config.model;

import java.util.Date;

public class Station {
    private String id;

    private String name;

    private String uid;

    private String deptid;

    private Date createdtime;

    private Date updatedtime;

    public Station(String id, String name, String uid, String deptid, Date createdtime, Date updatedtime) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.deptid = deptid;
        this.createdtime = createdtime;
        this.updatedtime = updatedtime;
    }

    public Station() {
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
}