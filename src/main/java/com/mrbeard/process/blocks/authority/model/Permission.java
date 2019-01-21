package com.mrbeard.process.blocks.authority.model;

import java.util.Date;

/**
 * @author 胡彬
 */
public class Permission {

    private String pid;

    private String pname;

    private Integer ptype;

    private String pvalue;

    private Date created;

    private Date updated;

    public Permission(String pid, String pname, Integer ptype, String pvalue, Date created, Date updated) {
        this.pid = pid;
        this.pname = pname;
        this.ptype = ptype;
        this.pvalue = pvalue;
        this.created = created;
        this.updated = updated;
    }

    public Permission() {
        super();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue == null ? null : pvalue.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}