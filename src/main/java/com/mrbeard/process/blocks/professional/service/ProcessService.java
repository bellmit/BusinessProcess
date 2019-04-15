package com.mrbeard.process.blocks.professional.service;

import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeDto;
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
}
