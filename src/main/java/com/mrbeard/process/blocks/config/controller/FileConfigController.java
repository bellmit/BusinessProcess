package com.mrbeard.process.blocks.config.controller;

import com.mrbeard.process.blocks.config.model.FileUrl;
import com.mrbeard.process.blocks.config.service.FilePathConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
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

    /**
     * 测试文件下载
     */
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public Result downloadFile(String type){
        String fileName = "";
        if(type == null || "".equals(type)){
            logger.error("type参数错误！");
            throw new RuntimeException("参数错误！");
        }
        try {
            switch (type) {
                case "G6PD": fileName = "G6PD.xlsx";break;
                case "PKU": fileName = "PKU.xlsx";break;
                case "CH": fileName = "CH.xlsx";break;
                case "OHP": fileName = "17_A_OHP.xlsx";break;
                default: throw new RuntimeException("type类型错误！（必须为：G6PD、PKU、CH、OHP中的一种）");
            }
            //获取下载地址
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            String root = request.getRealPath("");
            logger.info("项目根路径:"+root);
            logger.info("分隔符是："+File.separator);
            if (!root.endsWith(File.separator)) {
                root = root + File.separator;
                logger.info("不是以"+File.separator+"结尾");
            }
            String ur = root+File.separator+"excel"+File.separator+fileName;
            logger.info("下载模板地址为："+ur);
            //下载开始
            File downFiles = new File(ur);
            FileInputStream fis = new FileInputStream(downFiles);
            byte[] data = new byte[(int)downFiles.length()];
            int length = fis.read(data);
            fis.close();
            HttpServletResponse response = WebUtil.getResponse();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);
            response.setContentType("application/octet-stream");
            OutputStream fos = response.getOutputStream();
            fos.write(data);
            fis.close();
            fos.close();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }finally {
            return ResultGenerator.getSuccessResult("Successs");
        }
    }

}
