package com.mrbeard.process.blocks.config.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.dto.DeptmentDto;
import com.mrbeard.process.blocks.config.mapper.DepartmentMapper;
import com.mrbeard.process.blocks.config.model.Department;
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

    @Override
    public PageInfo<DeptmentDto> getDeptListWithPage(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<DeptmentDto> list = departmentDao.getDeptListWithPage();
            //将type转换
            list.forEach(deptmentDto -> {
                switch (deptmentDto.getType()){
                    case "1": deptmentDto.setType("一级");break;
                    case "2": deptmentDto.setType("二级");break;
                    case "3": deptmentDto.setType("三级");break;
                    case "4": deptmentDto.setType("四级");break;
                }
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
        return departmentDao.deleteByPrimaryKey(deptmentDto.getDeptId());
    }

    @Override
    public int updateSelective(DeptmentDto deptmentDto) {
        Department department = new Department();
        BeanUtil.copyProperties(deptmentDto,department);
        department.setId(deptmentDto.getDeptId());
        department.setName(deptmentDto.getDeptName());
        return departmentDao.updateByPrimaryKeySelective(department);
    }
}
