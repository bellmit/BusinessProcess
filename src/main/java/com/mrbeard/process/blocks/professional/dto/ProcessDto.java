package com.mrbeard.process.blocks.professional.dto;

import java.util.Date;

/**
 * @ClassName ProcessDto
 * @Description 流程Dto
 * @Author Mrbeard
 * @Date 2019/2/20 14:52
 * @Version 1.0
 **/
public class ProcessDto {
    /**
     * 流程标题
     */
    private String title;
    /**
     * 流程类型id
     */
    private String typeId;
    /**
     * 模板id
     */
    private String tempId;
    /**
     * 紧急程度
     */
    private Byte level;
    /**
     * 创建人id
     */
    private String createdId;
    /**
     * 处理状态
     */
    private Byte handleState;
    /**
     * 归档状态
     */
    private Byte fileState;
    /**
     * 流程状态
     */
    private Byte state;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
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
