package com.mrbeard.process.blocks.professional.model;

import java.util.Date;

public class ProcessNode {
    private String id;

    private String nodecode;

    private String nodename;

    private Byte nodetype;

    private String proid;

    private Byte nodestate;

    private Date createdtime;

    private Date updatedtime;

    private String nodebranch;

    private String currenthandlepersonid;

    public ProcessNode(String id, String nodecode, String nodename, Byte nodetype, String proid, Byte nodestate, Date createdtime, Date updatedtime, String nodebranch, String currenthandlepersonid) {
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
    }

    public ProcessNode() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNodecode() {
        return nodecode;
    }

    public void setNodecode(String nodecode) {
        this.nodecode = nodecode == null ? null : nodecode.trim();
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename == null ? null : nodename.trim();
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
        this.proid = proid == null ? null : proid.trim();
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
        this.nodebranch = nodebranch == null ? null : nodebranch.trim();
    }

    public String getCurrenthandlepersonid() {
        return currenthandlepersonid;
    }

    public void setCurrenthandlepersonid(String currenthandlepersonid) {
        this.currenthandlepersonid = currenthandlepersonid == null ? null : currenthandlepersonid.trim();
    }
}