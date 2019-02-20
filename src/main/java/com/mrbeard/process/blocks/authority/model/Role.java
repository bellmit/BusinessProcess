package com.mrbeard.process.blocks.authority.model;

import java.util.Date;

/**
 * @author 胡彬
 */
public class Role {
    /**
     * 角色id
     */
    private String rid;
    /**
     * 角色名
     */
    private String rname;
    /**
     * 角色描述
     */
    private String rdescription;

    private Date createdTime;

    private Date updatedTime;

    public Role(String rid, String rname, String rdescription, Date createdTime, Date updatedTime) {
        this.rid = rid;
        this.rname = rname;
        this.rdescription = rdescription;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Role() {
        super();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getRdescription() {
        return rdescription;
    }

    public void setRdescription(String rdescription) {
        this.rdescription = rdescription == null ? null : rdescription.trim();
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