package com.mrbeard.process.blocks.professional.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.mapper.ProcessMapper;
import com.mrbeard.process.blocks.professional.mapper.ProcessNodeBaseMapper;
import com.mrbeard.process.blocks.professional.mapper.ProcessNodeMapper;
import com.mrbeard.process.blocks.professional.model.Process;
import com.mrbeard.process.blocks.professional.model.ProcessNode;
import com.mrbeard.process.blocks.professional.model.ProcessNodeBase;
import com.mrbeard.process.blocks.professional.service.ProcessService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName ProcessServiceImpl
 * @Description 流程服务实现类
 * @Author Mrbeard
 * @Date 2019/2/20 17:02
 * @Version 1.0
 **/
@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private ProcessMapper processDao;

    @Resource
    private ProcessNodeMapper processNodeDao;
    
    @Resource
    private ProcessNodeBaseMapper processNodeBaseDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 创建新流程
     * @param processDto
     * @return
     * @throws ProcessRuntimeException
     */
    @Transactional(rollbackFor = ProcessRuntimeException.class)
    @Override
    public Result createProcess(ProcessDto processDto) throws ProcessRuntimeException {
        Process process = new Process();
        //设置数据
        String processId = setProcessData(processDto, process);
        //新建流程
        try {
            processDao.insertSelective(process);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("新建流程失败！");
        }
        try {
            //设置节点信息
            ProcessNode processNode = setProcessNode(processDto, processId);
            //插入节点信息
            processNodeDao.insertSelective(processNode);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("新建节点失败！");
        }
        //通知上级审批
        return ResultGenerator.getSuccessResult("新建流程成功");
    }

    /**
     * 设置流程初始节点信息
     * @param processDto
     */
    private ProcessNode setProcessNode(ProcessDto processDto,String processId)  {
        //获取开始节点名称属性
        ProcessNodeBase processNodeBase = processNodeBaseDao.selectByNodeType("1");
        //属性复制
        ProcessNode processNode = new ProcessNode();
        //复制参数设置
        CopyOptions options = new CopyOptions();
        options.setIgnoreNullValue(true);
        BeanUtil.copyProperties(processNodeBase,processNode,options);
        //设置节点
        processNode.setId(UUIDUtil.getUUID());
        processNode.setCreatedtime(new Date());
        processNode.setUpdatedtime(new Date());
        User userInfo = SessionUtil.getUserInfo();
        processNode.setCurrenthandlepersonid(userInfo.getUid());
        processNode.setNodebranch(processDto.getNodebranch());
        processNode.setNodestate((byte)0);
        processNode.setProid(processId);
        return processNode;
    }

    /**
     * 用于设置数据到Process中
     * @param processDto
     * @param process
     */
    private String setProcessData(ProcessDto processDto, Process process) {
        BeanUtils.copyProperties(processDto,process);
        //获取当前用户信息
        User userInfo = SessionUtil.getUserInfo();
        //设置创建用户id
        process.setcreatedId(userInfo.getUid());
        process.setCreatedTime(new Date());
        process.setUpdatedTime(new Date());
        process.setFileState((byte)0);
        process.setHandleState((byte)0);
        String processId = UUIDUtil.getUUID();
        process.setId(processId);
        process.setState((byte)1);
        return processId;
    }
}
