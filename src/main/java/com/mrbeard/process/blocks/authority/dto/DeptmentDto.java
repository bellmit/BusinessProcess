package com.mrbeard.process.blocks.authority.dto;

import com.mrbeard.process.blocks.config.model.Department;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DeptmentDto
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/4/13 0:36
 * @Version 1.0
 **/
public class DeptmentDto  extends Department implements Serializable {

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

    private String leaderName;

    private String parentsName;

    private String typeString;

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Byte getType() {
        return type;
    }

    @Override
    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getParentsId() {
        return parentsId;
    }

    @Override
    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    @Override
    public String getLeaderId() {
        return leaderId;
    }

    @Override
    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Byte getState() {
        return state;
    }

    @Override
    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    @Override
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
