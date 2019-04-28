package com.mrbeard.process.blocks.professional.controller;

import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.blocks.professional.model.Process;
import com.mrbeard.process.blocks.professional.model.ProcessNode;
import com.mrbeard.process.blocks.professional.service.ProcessService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ProcessController
 * @Description 流程controller
 * @Author Mrbeard
 * @Date 2019/2/20 14:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    /**
     * 新建流程
     * @param processDto
     * @return
     */
    @RequestMapping(value = "/createProcess",method = RequestMethod.POST)
    public Result createProcess(ProcessDto processDto){
        //校验参数
        if(ToolUtil.checkParamter(processDto.getLevel(),
                processDto.getTempId(),processDto.getTypeId(),processDto.getNodebranch(),processDto.getNodeTypeId()) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return processService.createProcess(processDto);
    }

    /**
     * 处理流程
     * @param processNodeDto
     * @return
     */
    @RequestMapping(value = "/postProcess",method = RequestMethod.POST)
    public Result postProcess(ProcessNodeDto processNodeDto){
        //校验参数
        if(ToolUtil.checkParamter(processNodeDto.getIsPass(),processNodeDto.getId()) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return processService.postProcess(processNodeDto);
    }

    /**
     * 获取流程类型列表
     * @return
     */
    @GetMapping("/getProcessTypeList")
    public Result getProcessTypeList(){
        return processService.getProcessTypeList();
    }

}
