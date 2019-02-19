package com.mrbeard.process.blocks.authority.model;

import java.util.Date;

/**
 * @author 胡彬
 */
public class Permission {

    private String pid;

    private String pname;

    private String pvalue;

    private Date created_time;

    private Date updated_time;

    public Permission(String pid, String pname, String pvalue, Date created_time, Date updated_time) {
        this.pid = pid;
        this.pname = pname;
        this.pvalue = pvalue;
        this.created_time = created_time;
        this.updated_time = updated_time;
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

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue == null ? null : pvalue.trim();
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