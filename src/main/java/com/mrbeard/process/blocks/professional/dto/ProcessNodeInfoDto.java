package com.mrbeard.process.blocks.professional.dto;

import java.util.Date;

/**
 * @ClassName ProcessNodeInfoDto
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/4/29 17:00
 * @Version 1.0
 **/
public class ProcessNodeInfoDto {
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
     * 紧急程度 1-正常 2-重要 3-紧急
     */
    private Byte level;
    /**
     * 创建人id
     */
    private String createdId;

    /**
     * 创建人id
     */
    private String createdName;
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

    /**
     * 是否拆分节点 0-不拆分 1-拆分  2-合并
     */
    private String nodebranch;

    /**
     * 节点类型id
     */
    private String nodeTypeId;

    /**
     * 流程唯一id
     */
    private String proId;

    /**
     * 节点id
     */
    private String nodeId;
    /**
     * 不通过原因
     */
    private String unpassReason;

    /**
     * 是否通过
     */
    private String isPass;


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

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
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

    public String getNodebranch() {
        return nodebranch;
    }

    public void setNodebranch(String nodebranch) {
        this.nodebranch = nodebranch;
    }

    public String getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(String nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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
}
