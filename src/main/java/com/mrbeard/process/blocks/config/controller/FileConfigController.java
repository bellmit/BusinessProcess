package com.mrbeard.process.blocks.config.controller;

import com.mrbeard.process.blocks.config.model.FileUrl;
import com.mrbeard.process.blocks.config.service.FilePathConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mrbeard
 * @date 2108-12-14
 * 文件配置controller
 */
@RestController
@RequestMapping("/api")
public class FileConfigController {

    @Autowired
    FilePathConfigService filePathConfigService;

    /**
     * 增改文件路径
     * @param urlConfig
     * @return
     */
    @RequestMapping(value = "/postFilePath",method = RequestMethod.POST)
    public Result postFilePath(FileUrl urlConfig) throws ProcessRuntimeException {
        return filePathConfigService.postFilePath(urlConfig);
    }


    /**
     * 获取文件路径列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/getFilePathList",method = RequestMethod.GET)
    public Result getFilePathList(Integer pageSize,Integer pageNum) throws ProcessRuntimeException {
        //参数校验
        if(ToolUtil.checkParamter(pageSize,pageNum)!=true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return filePathConfigService.getFilePathList(pageNum,pageSize);
    }
}
