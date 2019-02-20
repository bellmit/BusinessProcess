package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

public class Process {
    private String id;

    private String title;

    private String typeid;

    private String tempid;

    private Byte level;

    private String createdid;

    private Date createdTime;

    private Date updatedTime;

    private Byte handleState;

    private Byte fileState;

    private Byte state;

    public Process(String id, String title, String typeid, String tempid, Byte level, String createdid, Date createdTime, Date updatedTime, Byte handleState, Byte fileState, Byte state) {
        this.id = id;
        this.title = title;
        this.typeid = typeid;
        this.tempid = tempid;
        this.level = level;
        this.createdid = createdid;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.handleState = handleState;
        this.fileState = fileState;
        this.state = state;
    }

    public Process() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getTempid() {
        return tempid;
    }

    public void setTempid(String tempid) {
        this.tempid = tempid == null ? null : tempid.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getCreatedid() {
        return createdid;
    }

    public void setCreatedid(String createdid) {
        this.createdid = createdid == null ? null : createdid.trim();
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

    public Byte getHandleState() {
        return handleState;
    }

    public void setHandleState(Byte handleState) {
        this.handleState = handleState;
    }

    public Byte getFileState() {
        return fileState;
    }

    public void setFileState(Byte fileState) {
        this.fileState = fileState;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}