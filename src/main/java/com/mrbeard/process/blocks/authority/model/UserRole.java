package com.mrbeard.process.blocks.authority.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 胡彬
 */
public class UserRole  implements Serializable {
    private String uid;

    private String rid;

    private Date createdTime;

    private Date updatedTime;


    public UserRole(String uid, String rid, Date createdTime, Date updatedTime) {
        this.uid = uid;
        this.rid = rid;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public UserRole() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
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