package com.mrbeard.process.blocks.publish.model;

import java.util.Date;

/**
 * @author 胡彬
 */
public class Blog {
    private String id;

    private String title;

    private String content;

    private String publishuser;

    private Date created;

    private Date updated;

    public Blog(String id, String title, String content, String publishuser, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishuser = publishuser;
        this.created = created;
        this.updated = updated;
    }

    public Blog() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}