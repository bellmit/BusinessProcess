package com.mrbeard.process.blocks.config.controller;

import com.mrbeard.process.blocks.config.model.FileUrl;
import com.mrbeard.process.blocks.config.service.FilePathConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.FileUtil;
import com.mrbeard.process.util.SendMessageUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.WebUtil;
import net.sf.json.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * @author Mrbeard
 * @date 2108-12-14
 * 文件配置controller
 */
@RestController
@RequestMapping("/api")
public class FileConfigController {

    @Autowired
    SendMessageUtil sendMessageUtil;

    @Autowired
    FilePathConfigService filePathConfigService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @RequestMapping("/getRootPath")
    public Result getRootPath() throws ProcessRuntimeException{
        try {
            FileUtil.getWebRootContextPath();
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
        return ResultGenerator.getSuccessResult("Success!");
    }

    @RequestMapping(value = "/sendMessage",method = RequestMethod.GET)
    public Result sendMessage(){
        String [] params = {"2311","3"};
        String result = sendMessageUtil.sendMessage("15979807792", params);
        logger.info(result);
        return ResultGenerator.getSuccessResult(result);
    }

}
