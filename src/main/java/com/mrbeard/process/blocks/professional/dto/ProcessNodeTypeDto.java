package com.mrbeard.process.blocks.professional.dto;

import java.util.Date;

/**
 * @ClassName ProcessNodeTypeDto
 * @Description 流程节点类型Dto
 * @Author Mrbeard
 * @Date 2019/4/18 11:51
 * @Version 1.0
 **/
public class ProcessNodeTypeDto {

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

    private String sortName;

    private String sortCase;


    public ProcessNodeTypeDto() {
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

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortCase() {
        return sortCase;
    }

    public void setSortCase(String sortCase) {
        this.sortCase = sortCase;
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
