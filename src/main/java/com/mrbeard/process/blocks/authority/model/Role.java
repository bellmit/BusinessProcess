package com.mrbeard.process.blocks.authority.model;

import java.util.Date;

/**
 * @author 胡彬
 */
public class Role {
    private String rid;

    private String rname;

    private String rdescription;

    private String rvalue;

    private Date created;

    private Date updated;

    public Role(String rid, String rname, String rdescription, String rvalue, Date created, Date updated) {
        this.rid = rid;
        this.rname = rname;
        this.rdescription = rdescription;
        this.rvalue = rvalue;
        this.created = created;
        this.updated = updated;
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

    public String getRvalue() {
        return rvalue;
    }

    public void setRvalue(String rvalue) {
        this.rvalue = rvalue == null ? null : rvalue.trim();
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