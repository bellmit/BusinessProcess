package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

/**
 * 基础节点实体类
 */
public class ProcessNodeBase {
    private String id;

    private String nodename;

    private String nodecode;

    private Byte nodetype;

    private String description;

    private Date createdtime;

    private Date updatedtime;

    public ProcessNodeBase(String id, String nodename, String nodecode, Byte nodetype, String description, Date createdtime, Date updatedtime) {
        this.id = id;
        this.nodename = nodename;
        this.nodecode = nodecode;
        this.nodetype = nodetype;
        this.description = description;
        this.createdtime = createdtime;
        this.updatedtime = updatedtime;
    }

    public ProcessNodeBase() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename == null ? null : nodename.trim();
    }

    public String getNodecode() {
        return nodecode;
    }

    public void setNodecode(String nodecode) {
        this.nodecode = nodecode == null ? null : nodecode.trim();
    }

    public Byte getNodetype() {
        return nodetype;
    }

    public void setNodetype(Byte nodetype) {
        this.nodetype = nodetype;
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
}