package com.mrbeard.process.blocks.config.model;

import java.util.Date;

/**
 * @author Mrbeard
 * @date 2018-12-14
 */
public class FileUrl {
    private String id;

    private String url;

    private Integer type;

    private String name;

    private Date created;

    private Date updated;

    public FileUrl(String id, String url, String name, Date created, Date updated,  Integer type) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.created = created;
        this.updated = updated;
        this.name = name;
    }

    public FileUrl() {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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