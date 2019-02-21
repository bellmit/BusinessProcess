package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

public class Process {
    private String id;

    private String title;

    private String typeId;

    private String tempId;

    private Byte level;

    private String createdId;

    private Date createdTime;

    private Date updatedTime;

    private Byte handleState;

    private Byte fileState;

    private Byte state;

    public Process(String id, String title, String typeId, String tempId, Byte level, String createdId, Date createdTime, Date updatedTime, Byte handleState, Byte fileState, Byte state) {
        this.id = id;
        this.title = title;
        this.typeId = typeId;
        this.tempId = tempId;
        this.level = level;
        this.createdId = createdId;
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

    public String gettypeId() {
        return typeId;
    }

    public void settypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String gettempId() {
        return tempId;
    }

    public void settempId(String tempId) {
        this.tempId = tempId == null ? null : tempId.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getcreatedId() {
        return createdId;
    }

    public void setcreatedId(String createdId) {
        this.createdId = createdId == null ? null : createdId.trim();
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