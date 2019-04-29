package com.mrbeard.process.blocks.config.model;

import java.util.Date;

/**
 * 部门表
 */
public class Department {
    private String id;

    private String name;

    private Byte type;

    private String code;

    private String parentsId;

    private String leaderId;

    private String description;

    private Byte state;

    private Date createdTime;

    private Date updatedTime;

    public Department(){
    }

    public Department(String id, String name, Byte type, String code, String parentsId, String leaderId, String description, Byte state, Date createdTime, Date updatedTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
        this.parentsId = parentsId;
        this.leaderId = leaderId;
        this.description = description;
        this.state = state;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentsId() {
        return parentsId;
    }

    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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