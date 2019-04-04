package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

/**
 * 节点实体
 * @author Mrbeard
 */
public class ProcessNode {
    private String id;

    /**
     * 节点code
     */
    private String nodeCode;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 节点类型 1-开始节点 2-过程节点 3-结束节点
     */
    private Byte nodeType;

    /**
     * 流程id
     */
    private String proId;

    /**
     * 节节点状态  0-未处理 1-已处理
     */
    private Byte nodeState;

    private Date createdTime;

    private Date updatedTime;

    /**
     * 是否拆分节点 0-不拆分 1-拆分  2-合并
     */
    private String nodeBranch;

    /**
     * 当前处理人id
     */
    private String currentHandlePersonId;

    /**
     * 节点流程id
     */
    private String typeId;

    /**
     * 不通过原因
     */
    private String unpassReason;

    /**
     * 是否通过 0-否 1-是
     */
    private String isPass;

    /**
     * 是否开始节点 0-否 1-是
     */
    private String isBeginNode;

    /**
     * 是否结束节点 0-否 1-是
     */
    private String isEndNode;

    public ProcessNode() {
    }

    public ProcessNode(String id, String nodeCode, String nodeName, Byte nodeType, String proId, Byte nodeState, Date createdTime, Date updatedTime, String nodeBranch, String currentHandlePersonId, String typeId, String unpassReason, String isPass, String isBeginNode, String isEndNode) {
        this.id = id;
        this.nodeCode = nodeCode;
        this.nodeName = nodeName;
        this.nodeType = nodeType;
        this.proId = proId;
        this.nodeState = nodeState;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.nodeBranch = nodeBranch;
        this.currentHandlePersonId = currentHandlePersonId;
        this.typeId = typeId;
        this.unpassReason = unpassReason;
        this.isPass = isPass;
        this.isBeginNode = isBeginNode;
        this.isEndNode = isEndNode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Byte getNodeType() {
        return nodeType;
    }

    public void setNodeType(Byte nodeType) {
        this.nodeType = nodeType;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public Byte getNodeState() {
        return nodeState;
    }

    public void setNodeState(Byte nodeState) {
        this.nodeState = nodeState;
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

    public String getNodeBranch() {
        return nodeBranch;
    }

    public void setNodeBranch(String nodeBranch) {
        this.nodeBranch = nodeBranch;
    }

    public String getCurrentHandlePersonId() {
        return currentHandlePersonId;
    }

    public void setCurrentHandlePersonId(String currentHandlePersonId) {
        this.currentHandlePersonId = currentHandlePersonId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUnpassReason() {
        return unpassReason;
    }

    public void setUnpassReason(String unpassReason) {
        this.unpassReason = unpassReason;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getIsBeginNode() {
        return isBeginNode;
    }

    public void setIsBeginNode(String isBeginNode) {
        this.isBeginNode = isBeginNode;
    }

    public String getIsEndNode() {
        return isEndNode;
    }

    public void setIsEndNode(String isEndNode) {
        this.isEndNode = isEndNode;
    }
}