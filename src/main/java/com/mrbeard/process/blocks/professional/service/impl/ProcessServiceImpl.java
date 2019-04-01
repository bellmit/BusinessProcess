package com.mrbeard.process.blocks.professional.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.io.IoUtil;
import com.mrbeard.process.blocks.authority.mapper.UserLoginInfoMapper;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.authority.model.UserLoginInfo;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeDto;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
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
    @Resource
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 创建新流程
     * @param processDto
     * @return
     * @throws ProcessRuntimeException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result createProcess(ProcessDto processDto) throws ProcessRuntimeException {
        Process process = new Process();
        ProcessNode processNode = new ProcessNode();
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
            processNode = setStartProcessNode(processDto, processId,"1");
            //插入节点信息
            processNodeDao.insertSelective(processNode);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("新建节点失败！");
        }
        //通知对应负责人处理
        List<ProcessNodeTypeBase> processNodeTypeBases = noticeHigherUps(processNode);
        //更新节点信息
        processNode.setIsPass("1");
        updateNodeInfo(processNode,processNodeTypeBases);
        return ResultGenerator.getSuccessResult("新建流程成功");
    }


    /**
     * 获取对应负责人、并通知及时处理
     * @param processNode 节点信息
     */
    private List<ProcessNodeTypeBase> noticeHigherUps(ProcessNode processNode) {
        //获取当前节点typeId对应的nodeTypeBase
        ProcessNodeTypeBase nodeTypeBase = new ProcessNodeTypeBase();
        nodeTypeBase.setId(processNode.getTypeId());
        ProcessNodeTypeBase nodetypeBaseInData = processNodeTypeBaseDao.selectByCondition(nodeTypeBase);
        //用于存储下一个节点信息
        List<ProcessNodeTypeBase> processNodeTypeBases = new ArrayList<>();
        //判断是否为开始开始节点
        if("1".equals(nodetypeBaseInData.getIsBeginNode())){
            //获取下一个处理人，并通知
            processNodeTypeBases = processNodeTypeBaseDao.selectByParentsId(nodetypeBaseInData.getId());
        //判断是否为结束节点
        }else if("1".equals(nodetypeBaseInData.getIsEndNode())){
            //更新流程信息
            Process process = processDao.selectByPrimaryKey(processNode.getProId());
            process.setFileState((byte)1);
            process.setHandleState((byte)1);
            processDao.updateByPrimaryKeySelective(process);
            //更新节点信息
            processNode.setNodeState((byte)1);
            processNodeDao.updateByPrimaryKeySelective(processNode);
            return null;
        }else{
            //获取下一个处理人，并通知
            processNodeTypeBases = processNodeTypeBaseDao.selectByParentsId(nodetypeBaseInData.getId());
        }
        //通知
        processNodeTypeBases.forEach(processNodeTypeBase -> {
            String correlationId = processNodeTypeBase.getCorrelationId();
            noticeParentsHandle(correlationId);
        });
        return processNodeTypeBases;
    }

    /**
     * 更新节点信息
     * @param processNode
     */
    private void updateNodeInfo(ProcessNode processNode, List<ProcessNodeTypeBase> processNodeTypeBases) {
        List<ProcessNode> nodeList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(processNodeTypeBases)){
            //复制信息到下一个负责人信息
            for(ProcessNodeTypeBase processNodeTypeBase : processNodeTypeBases){
                ProcessNode processNodeNew = new ProcessNode();
                BeanUtil.copyProperties(processNode,processNodeNew);
                processNodeNew.setCurrentHandlePersonId(processNodeTypeBase.getCorrelationId());
                processNodeNew = setProcessNode(processNodeNew, "2");
                nodeList.add(processNodeNew);
            }
            //插入新节点
            processNodeDao.insertBatch(nodeList);
        }
        //更新节点信息
        processNode.setNodeState((byte)1);
        processNodeDao.updateByPrimaryKeySelective(processNode);
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
                Integer count = userLoginInfo.getSomeThingToDo();
                if(count.intValue() <= 0){
                    count = 1;
                }
                WebSocket.sendInfo("您有"+count+"条消息需要处理！",uid);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                throw new ProcessRuntimeException(e.getMessage());
            }
        }
        //不在线,更新需要通知数
        if(userLoginInfo != null && "0".equals(userLoginInfo.getIsonline())){
            Integer thingToDo = userLoginInfo.getSomeThingToDo();
            userLoginInfo.setSomeThingToDo(thingToDo+1);
            userLoginInfoDao.updateByPrimaryKeySelective(userLoginInfo);
        }
        //从未登陆过
        if(userLoginInfo == null){
            userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUid(uid);
            userLoginInfo.setIsonline("0");
            userLoginInfo.setIp(WebUtil.getRequest().getRemoteAddr());
            User user = userService.getUser(uid);
            userLoginInfo.setUname(user.getUname());
            userLoginInfo.setSomeThingToDo(1);
            userLoginInfoDao.insertSelective(userLoginInfo);
        }
    }

    /**
     * 设置节点信息
     * @param processNodeNew
     * @param nodeType 节点类型 1-开始节点 2-过程节点 3-结束节点
     * @return
     */
    private ProcessNode setProcessNode(ProcessNode processNodeNew,String nodeType) {
        processNodeNew.setId(UUIDUtil.getUUID());
        processNodeNew.setIsPass("0");
        processNodeNew.setNodeState((byte)0);
        ProcessNodeBase processNodeBase = processNodeBaseDao.selectByNodeType(nodeType);
        processNodeNew.setNodeName(processNodeBase.getNodeName());
        processNodeNew.setNodeCode(processNodeBase.getNodeCode());
        processNodeNew.setNodeType(processNodeBase.getNodeType());
        return processNodeNew;
    }

    /**
     * 设置流程初始节点信息
     * @param processDto
     * @param processId 流程id
     * @param nodeType 基础节点类型 1-开始节点 2-过程节点 3-结束节点
     */
    private ProcessNode setStartProcessNode(ProcessDto processDto,String processId,String nodeType)  {
        //从nodeBase中根据节点类型获取开始节点名称属性
        ProcessNodeBase processNodeBase = processNodeBaseDao.selectByNodeType(nodeType);
        //属性复制
        ProcessNode processNode = new ProcessNode();
        //复制参数设置
        CopyOptions options = new CopyOptions();
        options.setIgnoreNullValue(true);
        BeanUtil.copyProperties(processNodeBase,processNode,options);
        //设置节点
        processNode.setId(UUIDUtil.getUUID());
        processNode.setCreatedTime(new Date());
        User userInfo = SessionUtil.getUserInfo();
        processNode.setCurrentHandlePersonId(userInfo.getUid());
        processNode.setNodeBranch(processDto.getNodebranch());
        processNode.setNodeState((byte)0);
        processNode.setProId(processId);
        processNode.setTypeId(processDto.getNodeTypeId());
        return processNode;
    }



    /**
     * 用于设置流程初始数据到Process中
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
     * @param processNodeDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result postProcess(ProcessNodeDto processNodeDto) {
        //获取节点信息
        ProcessNode node = processNodeDao.selectByPrimaryKey(processNodeDto.getId());
        //设置节点信息
        node.setIsPass(processNodeDto.getIsPass());
        node.setUnpassReason(processNodeDto.getUnpassReason());
        //获取当前处理人
        String handlePersonId = node.getCurrentHandlePersonId();
        //判断当前处理人是否为最终节点
        ProcessNodeTypeBase nodeTypeBase = new ProcessNodeTypeBase();
        nodeTypeBase.setCorrelationId(node.getCurrentHandlePersonId());
        ProcessNodeTypeBase processNodeTypeBase = processNodeTypeBaseDao.selectByCondition(nodeTypeBase);
        //判断是否为最后一个节点
        boolean ifEndNode = ifEndNode(processNodeTypeBase);
        if(ifEndNode){
            //更新流程信息
            Process process = processDao.selectByPrimaryKey(node.getProId());
            //归档
            process.setFileState((byte)1);
            processDao.updateByPrimaryKeySelective(process);
            //更新节点
            updateNodeInfo(node,null);
        }
        //更新节点
        updateNodeInfo(node,null);
        return ResultGenerator.getSuccessResult("节点信息更新成功！");
    }

    /**
     * 判断是否为最后一个节点
     * @param processNodeTypeBase
     * @return
     */
    private boolean ifEndNode(ProcessNodeTypeBase processNodeTypeBase) {
        if(processNodeTypeBase != null){
            //isendnode是否为终结点 0-否 1-是
            //查找下一个节点是否为最后一个节点
            List<ProcessNodeTypeBase> processNodeTypeBases = processNodeTypeBaseDao.selectByParentsId(processNodeTypeBase.getId());
            for(ProcessNodeTypeBase nodeType : processNodeTypeBases){
                if("1".equals(nodeType.getIsEndNode())){
                    //为最后一个节点
                    return true;
                }
            }
            return false;
        }
        return false;
    }

}
