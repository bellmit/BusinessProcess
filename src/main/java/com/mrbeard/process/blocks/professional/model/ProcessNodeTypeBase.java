package com.mrbeard.process.blocks.professional.model;

public class ProcessNodeTypeBase {
    private String id;

    private String name;

    private String parentsid;

    private Byte isbeginnode;

    private String correlationid;

    public ProcessNodeTypeBase(String id, String name, String parentsid, Byte isbeginnode, String correlationid) {
        this.id = id;
        this.name = name;
        this.parentsid = parentsid;
        this.isbeginnode = isbeginnode;
        this.correlationid = correlationid;
    }

    public ProcessNodeTypeBase() {
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

    public String getParentsid() {
        return parentsid;
    }

    public void setParentsid(String parentsid) {
        this.parentsid = parentsid == null ? null : parentsid.trim();
    }

    public Byte getIsbeginnode() {
        return isbeginnode;
    }

    public void setIsbeginnode(Byte isbeginnode) {
        this.isbeginnode = isbeginnode;
    }

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid == null ? null : correlationid.trim();
    }
}