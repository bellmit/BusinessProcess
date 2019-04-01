package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

/**
 * 基础节点实体类
 */
public class ProcessNodeBase {
    private String id;

    private String nodeName;

    private String nodeCode;

    private Byte nodeType;

    private String description;

    private Date createdTime;

    private Date updatedTime;

    public ProcessNodeBase(String id, String nodeName, String nodeCode, Byte nodeType, String description, Date createdTime, Date updatedTime) {
        this.id = id;
        this.nodeName = nodeName;
        this.nodeCode = nodeCode;
        this.nodeType = nodeType;
        this.description = description;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public ProcessNodeBase() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public Byte getNodeType() {
        return nodeType;
    }

    public void setNodeType(Byte nodeType) {
        this.nodeType = nodeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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