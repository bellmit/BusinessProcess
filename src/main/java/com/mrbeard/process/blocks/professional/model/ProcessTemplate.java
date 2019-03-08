package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

public class ProcessTemplate {
    private String id;

    private Byte state;

    private String name;

    private String content;

    private String description;

    private Date createdtime;

    private Date updatedtime;

    private String processtype;

    public ProcessTemplate(String id, Byte state, String name, String content, String description, Date createdtime, Date updatedtime, String processtype) {
        this.id = id;
        this.state = state;
        this.name = name;
        this.content = content;
        this.description = description;
        this.createdtime = createdtime;
        this.updatedtime = updatedtime;
        this.processtype = processtype;
    }

    public ProcessTemplate() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public String getProcesstype() {
        return processtype;
    }

    public void setProcesstype(String processtype) {
        this.processtype = processtype == null ? null : processtype.trim();
    }
}