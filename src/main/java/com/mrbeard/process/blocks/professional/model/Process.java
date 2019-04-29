package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

/**
 * 流程信息实体类
 */
public class Process {
    /**
     * 唯一id
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型id
     */
    private String typeId;


    /**
     * 紧急程度 1-正常 2-重要 3-紧急
     */
    private Byte level;

    /**
     * 创建人id
     */
    private String createdId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 处理状态 0-待办 1-已办
     */
    private Byte handleState;

    /**
     * 归档状态 0-未归档 1-已归档
     */
    private Byte fileState;

    /**
     * 流程状态 0-无效 1-有效
     */
    private Byte state;

    public Process(String id, String title, String typeId, Byte level, String createdId, Date createdTime, Date updatedTime, Byte handleState, Byte fileState, Byte state) {
        this.id = id;
        this.title = title;
        this.typeId = typeId;
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