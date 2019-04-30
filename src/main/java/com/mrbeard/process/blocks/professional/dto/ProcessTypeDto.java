package com.mrbeard.process.blocks.professional.dto;

import com.mrbeard.process.blocks.professional.model.ProcessType;

import java.util.Date;

/**
 * @ClassName ProcessTypeDto
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/4/30 18:30
 * @Version 1.0
 **/
public class ProcessTypeDto extends ProcessType {
    private String id;

    private String typename;

    private String description;

    private String code;

    private Date createdTime;

    private Date updatedTime;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTypename() {
        return typename;
    }

    @Override
    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    @Override
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
