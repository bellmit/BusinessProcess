package com.mrbeard.process.blocks.professional.service.impl;

import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.mapper.ProcessMapper;
import com.mrbeard.process.blocks.professional.model.Process;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 创建新流程
     * @param processDto
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result createProcess(ProcessDto processDto) throws ProcessRuntimeException {
        Process process = new Process();
        //设置数据
        setProcessData(processDto,process);
        //新建流程
        try {
            processDao.insertSelective(process);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("新建流程失败！");
        }
        //设置节点

        //通知上级审批
        
        return ResultGenerator.getSuccessResult("新建流程成功");
    }

    /**
     * 用于设置数据到Process中
     * @param processDto
     * @param process
     */
    private void setProcessData(ProcessDto processDto, Process process) {
        BeanUtils.copyProperties(processDto,process);
        //获取当前用户信息
        User userInfo = SessionUtil.getUserInfo();
        //设置创建用户id
        process.setcreatedId(userInfo.getUid());
        process.setCreatedTime(new Date());
        process.setUpdatedTime(new Date());
        process.setFileState((byte)0);
        process.setHandleState((byte)0);
        process.setId(UUIDUtil.getUUID());
        process.setState((byte)1);
    }
}
