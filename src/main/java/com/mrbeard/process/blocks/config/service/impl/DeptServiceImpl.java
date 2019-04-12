package com.mrbeard.process.blocks.config.service.impl;

import com.mrbeard.process.blocks.authority.dto.DeptmentDto;
import com.mrbeard.process.blocks.authority.mapper.DepartmentMapper;
import com.mrbeard.process.blocks.authority.model.Department;
import com.mrbeard.process.blocks.config.service.DeptService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeptServiceImpl
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/4/13 0:30
 * @Version 1.0
 **/
@Service
public class DeptServiceImpl implements DeptService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    DepartmentMapper departmentDao;
    /**
     * 获取部门列表
     * @return
     */
    @Override
    public List<DeptmentDto> getDeptList() {
        try {
            return departmentDao.getDeptList();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("获取部门列表失败");
        }
    }
}