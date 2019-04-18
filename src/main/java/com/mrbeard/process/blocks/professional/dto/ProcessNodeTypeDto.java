package com.mrbeard.process.blocks.professional.dto;

/**
 * @ClassName ProcessNodeTypeDto
 * @Description 流程节点类型Dto
 * @Author Mrbeard
 * @Date 2019/4/18 11:51
 * @Version 1.0
 **/
public class ProcessNodeTypeDto {

    private String id;

    private String name;

    private String parentsId;

    private Byte isBeginNode;

    private Byte isEndNode;

    private String correlationId;

    private String processTypeId;

    public ProcessNodeTypeDto(String id, String name, String parentsId, Byte isBeginNode, Byte isEndNode, String correlationId,String processTypeId) {
        this.id = id;
        this.name = name;
        this.parentsId = parentsId;
        this.isBeginNode = isBeginNode;
        this.isEndNode = isEndNode;
        this.correlationId = correlationId;
        this.processTypeId = processTypeId;
    }

    public ProcessNodeTypeDto() {
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

    public String getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }
}
