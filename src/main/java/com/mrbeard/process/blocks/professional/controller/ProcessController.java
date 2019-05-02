package com.mrbeard.process.blocks.professional.controller;

import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.blocks.professional.dto.ProcessTypeDto;
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


    /**
     * 获取流程类型列表带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getProcessTypeListWithPage")
    public Result getProcessTypeListWithPage(Integer pageNum, Integer pageSize){
        return processService.getProcessTypeList(pageNum,pageSize);
    }


    /**
     * 配置流程类型
     * @param processTypeDto
     * @return
     */
    @RequestMapping(value = "/postProcessType",method = RequestMethod.POST)
    public Result postProcessType(ProcessTypeDto processTypeDto){
        return processService.postProcessType(processTypeDto);
    }



    /**
     * 获取对应用户未办事宜
     * @param uid
     * @return
     */
    @GetMapping("/getTodoList")
    public Result getTodoList(Integer pageNum, Integer pageSize, String uid){
        if(!ToolUtil.checkParamter(pageNum,pageSize,uid)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return processService.getTodoList(pageNum,pageSize,uid,"0");
    }


    /**
     * 获取对应用户已办办事宜
     * @param uid
     * @return
             */
    @GetMapping("/getHaddoList")
    public Result getHaddoList(Integer pageNum, Integer pageSize, String uid){
        if(!ToolUtil.checkParamter(pageNum,pageSize,uid)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return processService.getHaddoList(pageNum,pageSize,uid,"1");
    }


    /**
     * 获取用户创建的流程信息
     * @param pageNum
     * @param pageSize
     * @param uid
     * @return
     */
    @GetMapping("/getUserProcessInfo")
    public Result getUserProcessInfo(Integer pageNum, Integer pageSize, String uid){
        if(!ToolUtil.checkParamter(pageNum,pageSize,uid)){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return processService.getUserProcessInfo(pageNum,pageSize,uid);
    }



}
