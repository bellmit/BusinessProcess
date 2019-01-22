package com.mrbeard.process.blocks.config.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.config.model.FileUrl;
import com.mrbeard.process.blocks.config.service.FilePathConfigService;
import com.mrbeard.process.blocks.config.service.FileUrlService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author 胡彬
 * @Date 2018/12/7 11:00
 **/
@Service
public class FilePathConfigServiceImpl implements FilePathConfigService {

    @Resource
    FileUrlService fileUrlService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 设置文件路径
     * @param urlConfig
     */
    @Override
    public Result postFilePath(FileUrl urlConfig) throws ProcessRuntimeException {
        //判断是否有权限
        if(!ToolUtil.isHasPermission(Thread.currentThread().getStackTrace()[1].getMethodName())){
            return ResultGenerator.getErrorResult("权限不足！");
        }
        /**
         * 判断操作类型
         */
        if(ToolUtil.isNotEmpty(urlConfig.getId())){
            if(ToolUtil.checkIfAllParamtersExit(urlConfig.getName(),urlConfig.getUrl(),urlConfig.getType()) != true){
                //删除
                fileUrlService.deleteById(urlConfig.getId());
                return ResultGenerator.getSuccessResult("删除成功！");
            }
            //修改
            if(ToolUtil.checkParamter(urlConfig.getName(),urlConfig.getUrl(),urlConfig.getType()) != true){
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            //设置数据
            FileUrl fileUrlInData = fileUrlService.selectById(urlConfig.getId());
            fileUrlInData.setName(urlConfig.getName());
            fileUrlInData.setType(urlConfig.getType());
            fileUrlInData.setUrl(urlConfig.getUrl());
            fileUrlInData.setUpdated(new Date());
            //更新
            fileUrlService.updateById(fileUrlInData);
            return ResultGenerator.getSuccessResult("更新成功!");
        }
        //新增
        //参数判断
        if(ToolUtil.checkParamter(urlConfig.getType(),urlConfig.getUrl(),urlConfig.getName()) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        //设置数据
        urlConfig.setId(UUIDUtil.getUUID());
        urlConfig.setUpdated(new Date());
        urlConfig.setCreated(new Date());
        //新增
        fileUrlService.insert(urlConfig);
        //返回信息
        return ResultGenerator.getSuccessResult("设置路径成功！");
    }

    /**
     * 获取文件路径列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result getFilePathList(Integer pageNum, Integer pageSize) {
        //设置分页数据
        PageHelper.startPage(pageNum,pageSize);
        //查询
        List<FileUrl> fileUrlList = fileUrlService.selecFilePathtList();
        //返回分页信息
        PageInfo<FileUrl> pageInfo = new PageInfo<>(fileUrlList);
        return ResultGenerator.getSuccessResult(pageInfo);
    }
}
