package com.mrbeard.process.blocks.publish.model;

import java.util.Date;

/**
 * @author 胡彬
 */
public class Comment {
    private String id;

    private String publishuser;

    private Date created;

    private String belong;

    private Date updated;

    private String content;

    public Comment(String id, String publishuser, Date created, String belong, Date updated, String content) {
        this.id = id;
        this.publishuser = publishuser;
        this.created = created;
        this.belong = belong;
        this.updated = updated;
        this.content = content;
    }

    public Comment() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPublishuser() {
        return publishuser;
    }

    public void setPublishuser(String publishuser) {
        this.publishuser = publishuser == null ? null : publishuser.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}