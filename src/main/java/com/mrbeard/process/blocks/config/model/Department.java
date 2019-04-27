package com.mrbeard.process.blocks.config.model;

import java.util.Date;

/**
 * 部门表
 */
public class Department {
    private String id;

    private String name;

    private Byte type;

    private String code;

    private String parentsid;

    private String leaderid;

    private String description;

    private Byte state;

    private Date createdtime;

    private Date updatedtime;

    public Department(String id, String name, Byte type, String code, String parentsid, String leaderid, String description, Byte state, Date createdtime, Date updatedtime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
        this.parentsid = parentsid;
        this.leaderid = leaderid;
        this.description = description;
        this.state = state;
        this.createdtime = createdtime;
        this.updatedtime = updatedtime;
    }

    public Department() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getParentsid() {
        return parentsid;
    }

    public void setParentsid(String parentsid) {
        this.parentsid = parentsid == null ? null : parentsid.trim();
    }

    public String getLeaderid() {
        return leaderid;
    }

    public void setLeaderid(String leaderid) {
        this.leaderid = leaderid == null ? null : leaderid.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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
}