package com.mrbeard.process.blocks.config.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.dto.DeptmentDto;
import com.mrbeard.process.blocks.config.mapper.DepartmentMapper;
import com.mrbeard.process.blocks.config.model.Department;
import com.mrbeard.process.blocks.config.service.DeptService;
import com.mrbeard.process.exception.ProcessRuntimeException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Override
    public PageInfo<DeptmentDto> getDeptListWithPage(Integer pageNum, Integer pageSize,String name,String code) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            Department department = new Department();
            department.setName(name);
            department.setCode(code);
            List<DeptmentDto> list = departmentDao.getDeptListWithPage(department);
            List<DeptmentDto> copyList = new ArrayList<>();
            //将type转换
            list.forEach(deptmentDto -> {
                switch (deptmentDto.getType()){
                    case 1: deptmentDto.setTypeString("一级");break;
                    case 2: deptmentDto.setTypeString("二级");break;
                    case 3: deptmentDto.setTypeString("三级");break;
                    case 4: deptmentDto.setTypeString("四级");break;
                }
                copyList.add(deptmentDto);
            });
            //将parentsName设置
            copyList.forEach(copy ->{
                list.forEach(dto ->{
                    if(dto.getParentsId() != null && dto.getParentsId().equals(copy.getId())){
                        dto.setParentsName(copy.getName());
                    }
                });
            });
            PageInfo<DeptmentDto> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("获取部门信息失败！");
        }
    }

    @Override
    public int deleteSelective(DeptmentDto deptmentDto) {
        return departmentDao.deleteByPrimaryKey(deptmentDto.getId());
    }

    @Override
    public int insertSelective(DeptmentDto deptmentDto) {
        return departmentDao.insertSelective(deptmentDto);
    }

    @Override
    public int updateSelective(DeptmentDto deptmentDto) {
        return departmentDao.updateByPrimaryKeySelective(deptmentDto);
    }
}
