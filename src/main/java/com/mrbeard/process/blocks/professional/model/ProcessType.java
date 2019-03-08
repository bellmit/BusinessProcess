package com.mrbeard.process.blocks.professional.model;

public class ProcessType {
    private String id;

    private String typename;

    private String description;

    private String code;

    public ProcessType(String id, String typename, String description,String code) {
        this.id = id;
        this.typename = typename;
        this.description = description;
        this.code = code;
    }

    public ProcessType() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}