package com.mrbeard.process.blocks.professional.controller;

import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.service.ProcessService;
import com.mrbeard.process.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/createProcess",method = RequestMethod.POST)
    public Result createProcess(ProcessDto processDto){
        return processService.createProcess(processDto);
    }

}
