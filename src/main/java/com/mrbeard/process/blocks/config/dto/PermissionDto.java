package com.mrbeard.process.blocks.config.dto;

import java.util.Date;

/**
 * @ClassName PermissionDto
 * @Description 用于返回带有标志位Dto
 * @Author Mrbeard
 * @Date 2019/1/23 9:54
 * @Version 1.0
 **/
public class PermissionDto {
    private String pid;

    private String pname;

    private Integer ptype;

    private String pvalue;

    private Date created;

    private Date updated;

    private Boolean isUsed;

    public PermissionDto() {
    }

    public PermissionDto(String pid, String pname, Integer ptype, String pvalue, Date created, Date updated, Boolean isUsed) {
        this.pid = pid;
        this.pname = pname;
        this.ptype = ptype;
        this.pvalue = pvalue;
        this.created = created;
        this.updated = updated;
        this.isUsed = isUsed;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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
        this.pvalue = pvalue;
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

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }


    @Override
    public String toString() {
        return "PermissionDto{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", ptype=" + ptype +
                ", pvalue='" + pvalue + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", isUsed=" + isUsed +
                '}';
    }
}
