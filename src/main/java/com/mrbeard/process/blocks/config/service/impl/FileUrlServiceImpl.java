package com.mrbeard.process.blocks.config.service.impl;

import com.mrbeard.process.blocks.config.mapper.FileUrlConfigMapper;
import com.mrbeard.process.blocks.config.model.FileUrl;
import com.mrbeard.process.blocks.config.service.FileUrlService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName FileUrlServiceImpl
 * @Description TODO
 * @Author Mrbeard
 * @Date 2018/12/17 18:38
 * @Version 1.0
 **/
@Service
public class FileUrlServiceImpl implements FileUrlService {
    @Resource
    FileUrlConfigMapper fileUrlConfigDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 删除
     * @param id
     * @throws ProcessRuntimeException
     */
    @Override
    public void deleteById(String id) throws ProcessRuntimeException {
        try {
            fileUrlConfigDao.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查找一条
     * @param id
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public FileUrl selectById(String id) throws ProcessRuntimeException {
        try {
            return fileUrlConfigDao.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新
     * @param fileUrlInData
     * @throws ProcessRuntimeException
     */
    @Override
    public void updateById(FileUrl fileUrlInData) throws ProcessRuntimeException {
        try {
            fileUrlConfigDao.updateByPrimaryKey(fileUrlInData);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增
     * @param urlConfig
     * @throws ProcessRuntimeException
     */
    @Override
    public void insert(FileUrl urlConfig) throws ProcessRuntimeException {
        try {
            fileUrlConfigDao.insert(urlConfig);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查找列表
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public List<FileUrl> selecFilePathtList() throws ProcessRuntimeException {
        try {
            return fileUrlConfigDao.selectList();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
