package com.mrbeard.process.blocks.publish.model;

import java.util.Date;

/**
 * @author 胡彬
 */
public class Photo {
    private String id;

    private String url;

    private String belong;

    private Date created;

    private Date updated;

    private Integer belongtype;

    public Photo(String id, String url, String belong, Date created, Date updated, Integer belongtype) {
        this.id = id;
        this.url = url;
        this.belong = belong;
        this.created = created;
        this.updated = updated;
        this.belongtype = belongtype;
    }

    public Photo() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
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

    public Integer getBelongtype() {
        return belongtype;
    }

    public void setBelongtype(Integer belongtype) {
        this.belongtype = belongtype;
    }
}