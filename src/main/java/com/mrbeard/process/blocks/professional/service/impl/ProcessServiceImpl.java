package com.mrbeard.process.blocks.professional.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.io.IoUtil;
import com.mrbeard.process.blocks.authority.mapper.UserLoginInfoMapper;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.authority.model.UserLoginInfo;
import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.mapper.ProcessMapper;
import com.mrbeard.process.blocks.professional.mapper.ProcessNodeBaseMapper;
import com.mrbeard.process.blocks.professional.mapper.ProcessNodeMapper;
import com.mrbeard.process.blocks.professional.mapper.ProcessNodeTypeBaseMapper;
import com.mrbeard.process.blocks.professional.model.Process;
import com.mrbeard.process.blocks.professional.model.ProcessNode;
import com.mrbeard.process.blocks.professional.model.ProcessNodeBase;
import com.mrbeard.process.blocks.professional.model.ProcessNodeTypeBase;
import com.mrbeard.process.blocks.professional.service.ProcessService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.common.WebSocket;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import com.mrbeard.process.util.WebUtil;
import org.apache.tomcat.Jar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Condition;

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

    @Resource
    private ProcessNodeTypeBaseMapper processNodeTypeBaseDao;

    @Resource
    private UserLoginInfoMapper userLoginInfoDao;

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
        String processId = setStartProcessData(processDto, process);
        //新建流程
        try {
            processDao.insertSelective(process);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("新建流程失败！");
        }
        try {
            //设置节点信息
            ProcessNode processNode = setStartProcessNode(processDto, processId,"1");
            //插入节点信息
            processNodeDao.insertSelective(processNode);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("新建节点失败！");
        }
        //通知对应负责人处理
        noticeHigherUps(processId);
        return ResultGenerator.getSuccessResult("新建流程成功");
    }


    /**
     * 通知对应负责人及时处理
     * @param processId 流程id
     */
    private void noticeHigherUps(String processId) {
        //根据流程id查找对应的节点信息
        ProcessNode condition = new ProcessNode();
        condition.setProid(processId);
        condition.setNodestate((byte)0);
        ProcessNode processNode = processNodeDao.selectByCondition(condition);
        //查询节点(判断是否当前未处理人为创建人)
        if(processNode.getCurrenthandlepersonid().equals(SessionUtil.getUserInfo().getUid())){
            //将流程进行下去，通知对应负责人，进行处理
            //获取当前流程类型
            ProcessNodeTypeBase processNodeTypeBase = processNodeTypeBaseDao.selectByPrimaryKey(processNode.getTypeId());
            //获取相应负责人
            List<ProcessNodeTypeBase> processNodeTypeBases = processNodeTypeBaseDao.selectPersonIds(processNodeTypeBase.getId());
            //通知相应的负责人
            processNodeTypeBases.forEach(nodeTypeBase -> {
                //通知，并更新用户登陆信息
                noticeParentsHandle(nodeTypeBase.getId());
            });
            //通知完后更新节点信息
            processNode.setIsPass("1");
            updateNodeInfo(processNode);
        }
    }

    /**
     * 更新节点信息
     * @param processNode
     */
    private void updateNodeInfo(ProcessNode processNode) {
        //获取节点是否审核通过 0-不通过 1-通过
        if("0".equals(processNode.getIsPass())){
            //未通过，通知当前处理人
            try {
                WebSocket.sendInfo("您有一条消息需要处理！",processNode.getCurrenthandlepersonid());
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                throw new ProcessRuntimeException(e.getMessage());
            }
            //更新节点信息
            processNodeDao.updateByPrimaryKeySelective(processNode);
            //终止对应的流程
            Process process = processDao.selectByPrimaryKey(processNode.getProid());
            process.setFileState((byte)1);
            process.setState((byte)0);
            processDao.updateByPrimaryKeySelective(process);
        }
        //获取负责人id
        List<ProcessNodeTypeBase> nodeTypeBases = processNodeTypeBaseDao.selectPersonIds(processNode.getTypeId());
        List<ProcessNode> nodeList = new ArrayList<>();
        nodeTypeBases.forEach(processNodeTypeBase -> {
            ProcessNode processNodeNew = new ProcessNode();
            BeanUtil.copyProperties(processNode,processNodeNew);
            processNodeNew.setCurrenthandlepersonid(processNodeTypeBase.getId());
            processNodeNew = setProcessNode(processNodeNew);
            nodeList.add(processNodeNew);
        });
        //将当前处理人节点结束
        processNode.setNodestate((byte)1);
        processNodeDao.updateByPrimaryKeySelective(processNode);
        //开始新的节点,插入新的节点
        processNodeDao.insertBatch(nodeList);
        nodeList.forEach(node->{
            //通知负责人处理
            noticeParentsHandle(node.getCurrenthandlepersonid());
        });
    }

    /**
     * 通知当前处理人
     * @param uid
     */
    private void noticeParentsHandle(String uid) {
        //判断当前id是否在线
        UserLoginInfo userLoginInfo = userLoginInfoDao.selectByPrimaryKey(uid);
        //在线
        if(userLoginInfo != null && "1".equals(userLoginInfo.getIsonline())){
            //通知
            try {
                WebSocket.sendInfo("您有"+userLoginInfo.getSomeThingToDo()+"条消息需要处理！",uid);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                throw new ProcessRuntimeException(e.getMessage());
            }
        }
        //从未登陆过
        if(userLoginInfo == null){
            userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUid(uid);
            userLoginInfo.setIsonline("0");
            userLoginInfo.setIp(WebUtil.getRequest().getRemoteAddr());
            userLoginInfo.setUname(SessionUtil.getUserInfo().getUname());
            userLoginInfo.setSomeThingToDo(1);
        }
        //不在线,更新需要通知数
        Integer thingToDo = userLoginInfo.getSomeThingToDo();
        userLoginInfo.setSomeThingToDo(thingToDo+1);
        userLoginInfoDao.updateByPrimaryKeySelective(userLoginInfo);
    }

    /**
     * 设置节点信息
     * @param processNodeNew
     * @return
     */
    private ProcessNode setProcessNode(ProcessNode processNodeNew) {
        processNodeNew.setId(UUIDUtil.getUUID());
        processNodeNew.setIsPass("0");
        processNodeNew.setNodestate((byte)0);
        ProcessNodeBase processNodeBase = processNodeBaseDao.selectByNodeType("2");
        processNodeNew.setNodename(processNodeBase.getNodename());
        processNodeNew.setNodecode(processNodeBase.getNodecode());
        processNodeNew.setNodetype(processNodeBase.getNodetype());
        return processNodeNew;
    }

    /**
     * 设置流程初始节点信息
     * @param processDto
     */
    private ProcessNode setStartProcessNode(ProcessDto processDto,String processId,String nodeType)  {
        //获取开始节点名称属性
        ProcessNodeBase processNodeBase = processNodeBaseDao.selectByNodeType(nodeType);
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
        processNode.setTypeId(processDto.getNodeTypeId());
        return processNode;
    }



    /**
     * 用于设置数据到Process中
     * @param processDto
     * @param process
     */
    private String setStartProcessData(ProcessDto processDto, Process process) {
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


    /**
     * 处理流程
     * @param processNode
     * @return
     */
    @Override
    public Result postProcess(ProcessNode processNode) {
        //设置节点信息
        processNode.setCreatedtime(new Date());
        ProcessNode condition = new ProcessNode();
        condition.setProid(processNode.getProid());
        List<ProcessNodeTypeBase> nodeTypeBase = processNodeTypeBaseDao.selectByParentsId(processNode.getCurrenthandlepersonid());
        //获取上一节点信息
        processNodeDao.selectByCondition(condition);
        //更新节点
        //判断流程是否完成
        return ResultGenerator.getSuccessResult("success");
    }
}
