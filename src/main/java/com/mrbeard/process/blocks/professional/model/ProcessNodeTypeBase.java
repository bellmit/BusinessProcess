package com.mrbeard.process.blocks.professional.model;

public class ProcessNodeTypeBase {
    private String id;

    private String name;

    private String parentsId;

    private Byte isBeginNode;

    private Byte isEndNode;

    private String correlationId;

    public ProcessNodeTypeBase(String id, String name, String parentsId, Byte isBeginNode, Byte isEndNode, String correlationId) {
        this.id = id;
        this.name = name;
        this.parentsId = parentsId;
        this.isBeginNode = isBeginNode;
        this.isEndNode = isEndNode;
        this.correlationId = correlationId;
    }

    public ProcessNodeTypeBase() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentsId() {
        return parentsId;
    }

    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    public Byte getIsBeginNode() {
        return isBeginNode;
    }

    public void setIsBeginNode(Byte isBeginNode) {
        this.isBeginNode = isBeginNode;
    }

    public Byte getIsEndNode() {
        return isEndNode;
    }

    public void setIsEndNode(Byte isEndNode) {
        this.isEndNode = isEndNode;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}