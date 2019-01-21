package com.mrbeard.process.blocks.authority.model;

import java.io.Serializable;

/**
 * @author 胡彬
 */
public class RolePermission implements Serializable {
    private String rid;

    private String pid;

    public RolePermission(String rid, String pid) {
        this.rid = rid;
        this.pid = pid;
    }

    public RolePermission() {
        super();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}