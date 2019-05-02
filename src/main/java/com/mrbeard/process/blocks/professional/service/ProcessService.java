package com.mrbeard.process.blocks.professional.service;

import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.blocks.professional.dto.ProcessTypeDto;
import com.mrbeard.process.blocks.professional.model.ProcessNode;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import org.springframework.stereotype.Service;

/**
 * @ClassName ProcessSerivice
 * @Description 流程
 * @Author Mrbeard
 * @Date 2019/1/31 14:18
 * @Version 1.0
 **/
public interface ProcessService {

    /**
     * 创建新流程
     * @param processDto
     * @return
     * @throws ProcessRuntimeException
     */
    Result createProcess(ProcessDto processDto) throws ProcessRuntimeException;

    /**
     * 处理流程
     * @param processNodeDto
     * @return
     */
    Result postProcess(ProcessNodeDto processNodeDto);

    /**
     * 获取流程类型列表
     * @return
     */
    Result getProcessTypeList();

    /**
     * 获取流程类型列表带分页
     * @return
     */
    Result getProcessTypeList(Integer pageNum,Integer pageSize);


    /**
     * 获取对应用户未办事宜
     * @param uid
     * @return
     */
    Result getTodoList(Integer pageNum, Integer pageSize, String uid ,String nodeState);

    /**
     * 获取对应用户已办事宜
     * @param uid
     * @return
     */
    Result getHaddoList(Integer pageNum, Integer pageSize, String uid, String nodeState);

    /**
     * 获取用户创建的流程信息
     * @param pageNum
     * @param pageSize
     * @param uid
     * @return
     */
    Result getUserProcessInfo(Integer pageNum, Integer pageSize, String uid);

    /**
     * 配置流程类型信息
     * @param processTypeDto
     * @return
     */
    Result postProcessType(ProcessTypeDto processTypeDto);
}
