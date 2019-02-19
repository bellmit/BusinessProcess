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

    private Date created_time;

    private Date updated_time;

    public Role(String rid, String rname, String rdescription, Date created_time, Date updated_time) {
        this.rid = rid;
        this.rname = rname;
        this.rdescription = rdescription;
        this.created_time = created_time;
        this.updated_time = updated_time;
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

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
    }
}