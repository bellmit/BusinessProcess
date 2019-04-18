package com.mrbeard.process.blocks.professional.service;

import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.result.Result;

/**
 * @InterFaceName NodeService
 * @Description 节点service
 * @Author Mrbeard
 * @Date 2019/4/18 14:17
 * @Version 1.0
 **/
public interface NodeService {

    /**
     * 获取流程节点类型列表
     * @return
     */
    Result getProcessNodeTypeList();

    /**
     * 配置节点类型
     * @param nodeTypeDto
     * @return
     */
    Result postNodeType(ProcessNodeTypeDto nodeTypeDto);

    /**
     * 获取流程节点类型列表,通过processTypeId
     * @return
     */
    Result getProcessNodeTypeList(String processTypeId,Integer pageNum, Integer pageSize);
}
