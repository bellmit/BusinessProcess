package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

public class ProcessType {
    private String id;

    private String typename;

    private String description;

    private String code;

    private Date createdTime;

    private Date updatedTime;

    public ProcessType(String id, String typename, String description,String code,Date createdTime,Date updatedTime) {
        this.id = id;
        this.typename = typename;
        this.description = description;
        this.code = code;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public ProcessType() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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