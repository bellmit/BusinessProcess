package com.mrbeard.process.blocks.authority.model;

import java.io.Serializable;

/**
 * @author 胡彬
 */
public class UserRole  implements Serializable {
    private String uid;

    private String rid;

    public UserRole(String uid, String rid) {
        this.uid = uid;
        this.rid = rid;
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
}