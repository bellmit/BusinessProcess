package com.mrbeard.process.blocks.professional.controller;

import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.blocks.professional.service.NodeService;
import com.mrbeard.process.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName NodeController
 * @Description 节点
 * @Author Mrbeard
 * @Date 2019/4/18 14:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class NodeController {

    @Autowired
    private NodeService nodeService;
    /**
     * 获取流程节点类型列表
     * @return
     */
    @GetMapping("/getProcessNodeTypeList")
    public Result getProcessNodeTypeList(){
        return nodeService.getProcessNodeTypeList();
    }


    /**
     * 配置节点类型
     * @param nodeTypeDto
     * @return
     */
    @RequestMapping(value = "/postNodeType", method = RequestMethod.POST)
    public Result postNodeType(ProcessNodeTypeDto nodeTypeDto){
        return nodeService.postNodeType(nodeTypeDto);
    }

    /**
     * 获取流程节点类型列表
     * @param processTypeId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getProcessNodeTypeBeginList")
    public Result getProcessNodeTypeList(String processTypeId,Integer pageNum, Integer pageSize){
        return nodeService.getProcessNodeTypeList(processTypeId,pageNum,pageSize);
    }

}
