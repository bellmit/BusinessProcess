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
    private String nodecode;

    /**
     * 节点名称
     */
    private String nodename;

    /**
     * 节点类型 1-开始节点 2-过程节点 3-结束节点
     */
    private Byte nodetype;

    /**
     * 流程id
     */
    private String proid;

    /**
     * 节节点状态  0-未处理 1-已处理
     */
    private Byte nodestate;

    private Date createdtime;

    private Date updatedtime;

    /**
     * 是否拆分节点 0-不拆分 1-拆分  2-合并
     */
    private String nodebranch;

    /**
     * 当前处理人id
     */
    private String currenthandlepersonid;

    /**
     * 节点流程id
     */
    private String typeId;

    /**
     * 不通过原因
     */
    private String unpassReason;

    /**
     * 是否通过
     */
    private String isPass;

    public ProcessNode() {
    }

    public ProcessNode(String id, String nodecode, String nodename, Byte nodetype, String proid, Byte nodestate, Date createdtime, Date updatedtime, String nodebranch, String currenthandlepersonid, String typeId, String unpassReason, String isPass) {
        this.id = id;
        this.nodecode = nodecode;
        this.nodename = nodename;
        this.nodetype = nodetype;
        this.proid = proid;
        this.nodestate = nodestate;
        this.createdtime = createdtime;
        this.updatedtime = updatedtime;
        this.nodebranch = nodebranch;
        this.currenthandlepersonid = currenthandlepersonid;
        this.typeId = typeId;
        this.unpassReason = unpassReason;
        this.isPass = isPass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodecode() {
        return nodecode;
    }

    public void setNodecode(String nodecode) {
        this.nodecode = nodecode;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Byte getNodetype() {
        return nodetype;
    }

    public void setNodetype(Byte nodetype) {
        this.nodetype = nodetype;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public Byte getNodestate() {
        return nodestate;
    }

    public void setNodestate(Byte nodestate) {
        this.nodestate = nodestate;
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

    public String getNodebranch() {
        return nodebranch;
    }

    public void setNodebranch(String nodebranch) {
        this.nodebranch = nodebranch;
    }

    public String getCurrenthandlepersonid() {
        return currenthandlepersonid;
    }

    public void setCurrenthandlepersonid(String currenthandlepersonid) {
        this.currenthandlepersonid = currenthandlepersonid;
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
}