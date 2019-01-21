package com.mrbeard.process.blocks.config.service;


import com.mrbeard.process.blocks.config.model.FileUrl;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

/**
 * @Author 胡彬
 * @Date 2018/12/7 10:58
 * 文件路径配置
 **/
public interface FilePathConfigService {

    /**
     *  设置文件路径（只到文件夹）
     * @param urlConfig
     * @return
     * @throws ProcessRuntimeException
     */
    Result postFilePath(FileUrl urlConfig) throws ProcessRuntimeException;


    /**
     * 获取设置的文件路径列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result getFilePathList(Integer pageNum, Integer pageSize);
}
