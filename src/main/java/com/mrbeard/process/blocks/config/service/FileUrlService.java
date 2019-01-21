package com.mrbeard.process.blocks.config.service;


import com.mrbeard.process.blocks.config.model.FileUrl;
import com.mrbeard.process.exception.ProcessRuntimeException;

import java.util.List;

/**
 * @InterFaceName FileUrlService
 * @Description TODO
 * @Author Mrbeard
 * @Date 2018/12/17 18:38
 * @Version 1.0
 **/
public interface FileUrlService {


    /**
     * 删除
     * @param id
     * @throws ProcessRuntimeException
     */
    void deleteById(String id) throws ProcessRuntimeException;

    /**
     * 查找
     * @param id
     * @return
     * @throws ProcessRuntimeException
     */
    FileUrl selectById(String id) throws ProcessRuntimeException;

    /**
     * 更新
     * @param fileUrlInData
     * @throws ProcessRuntimeException
     */
    void updateById(FileUrl fileUrlInData)throws ProcessRuntimeException;

    /**
     * 新增
     * @throws ProcessRuntimeException
     * @param urlConfig
     */
    void insert(FileUrl urlConfig)throws ProcessRuntimeException;

    /**
     * 查找列表
     * @return
     * @throws ProcessRuntimeException
     */
    List<FileUrl> selecFilePathtList() throws ProcessRuntimeException;
}
