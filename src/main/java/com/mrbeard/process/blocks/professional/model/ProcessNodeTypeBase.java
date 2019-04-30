package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

/**
 * 流程基础类型实体类
 * @author Mrbeard
 */
public class ProcessNodeTypeBase {
    private String id;

    private String name;

    private String parentsId;

    private String parentsName;

    private Byte isBeginNode;

    private Byte isEndNode;

    private String correlationId;

    private String correlationName;

    private String processTypeId;

    private String nodeTypeQueue;

    private Date createdTime;

    private Date updatedTime;

    public ProcessNodeTypeBase(String id, String name, String parentsId, Byte isBeginNode,
                               Byte isEndNode, String correlationId,String processTypeId,
                               String nodeTypeQueue,String parentsName,String correlationName,
                               Date createdTime, Date updatedTime) {
        this.id = id;
        this.name = name;
        this.parentsId = parentsId;
        this.isBeginNode = isBeginNode;
        this.isEndNode = isEndNode;
        this.correlationId = correlationId;
        this.processTypeId = processTypeId;
        this.nodeTypeQueue = nodeTypeQueue;
        this.parentsName = parentsName;
        this.correlationName = correlationName;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public ProcessNodeTypeBase() {
        super();
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

    public String getParentsId() {
        return parentsId;
    }

    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    public Byte getIsBeginNode() {
        return isBeginNode;
    }

    public void setIsBeginNode(Byte isBeginNode) {
        this.isBeginNode = isBeginNode;
    }

    public Byte getIsEndNode() {
        return isEndNode;
    }

    public void setIsEndNode(Byte isEndNode) {
        this.isEndNode = isEndNode;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getNodeTypeQueue() {
        return nodeTypeQueue;
    }

    public void setNodeTypeQueue(String nodeTypeQueue) {
        this.nodeTypeQueue = nodeTypeQueue;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getCorrelationName() {
        return correlationName;
    }

    public void setCorrelationName(String correlationName) {
        this.correlationName = correlationName;
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