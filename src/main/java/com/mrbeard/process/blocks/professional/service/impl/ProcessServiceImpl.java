package com.mrbeard.process.blocks.professional.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.mrbeard.process.blocks.authority.mapper.UserLoginInfoMapper;
import com.mrbeard.process.blocks.config.model.User;
import com.mrbeard.process.blocks.authority.model.UserLoginInfo;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.blocks.professional.dto.ProcessDto;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeDto;
import com.mrbeard.process.blocks.professional.mapper.*;
import com.mrbeard.process.blocks.professional.model.*;
import com.mrbeard.process.blocks.professional.model.Process;
import com.mrbeard.process.blocks.professional.service.ProcessService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.common.WebSocket;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.UUIDUtil;
import com.mrbeard.process.util.WebUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Resource
    private ProcessTypeMapper processTypeDao;

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
        //设置流程数据
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
            processNode = setProcessNode(processDto, processId,"1");
            processNodeDao.insertSelective(processNode);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException("新建节点失败！");
        }
        //更新节点信息
        //初始化节点设置节点为通过
        processNode.setIsPass("1");
        updateNodeInfo(processNode);
        //通知对应负责人处理
        noticeHigherUps(processNode);
        return ResultGenerator.getSuccessResult("新建流程成功！");
    }


    /**
     * 获取对应负责人、并通知及时处理
     * @param processNode 节点信息
     */
    private void noticeHigherUps(ProcessNode processNode) {
        //用于存储下一（多个）个节点信息
        List<ProcessNodeTypeBase> processNodeTypeBases = new ArrayList<>();
        //判断下个节点是否为结束节点
        processNodeTypeBases = processNodeTypeBaseDao.selectByParentsId(processNode.getTypeId());
        for(ProcessNodeTypeBase nodeTypeBase : processNodeTypeBases){
            if(nodeTypeBase.getIsEndNode() == 1){
                return;
            }
        }
        //获取下一个处理人，并通知
        //通知
        processNodeTypeBases.forEach(processNodeTypeBase -> {
            String correlationId = processNodeTypeBase.getCorrelationId();
            noticeParentsHandle(correlationId);
        });
    }

    /**
     * 更新节点信息
     * @param processNode 当前节点信息
     */
    private void updateNodeInfo(ProcessNode processNode) {
        List<ProcessNode> nodeList = new ArrayList<>();
        //判断节点是否拆分、合并 0-不拆分 1-拆分 2-合并
        switch (processNode.getNodeBranch()){
            case "0" :
                //判断是否通过
                if("0".equals(processNode.getIsPass())){
                    //更新流程为终结状态
                    endingProcess(processNode);
                    //设置当前节点为终结状态
                    processNode.setIsEndNode("1");
                    //更新
                    processNodeDao.updateByPrimaryKeySelective(processNode);
                }
                //通过
                break;
            case "1" : break;
            case "2" :
                //判断是否所有上一个节点都通过
                ProcessNodeTypeBase condition = new ProcessNodeTypeBase();
                condition.setId(processNode.getTypeId());
                ProcessNodeTypeBase processNodeTypeBases = processNodeTypeBaseDao.selectByCondition(condition);
                //获取所有上级typeId
                List<String> typeIds = new ArrayList<>();
                String [] parentsId = processNodeTypeBases.getParentsId().split(",");
                for(int i = 0; i < parentsId.length; i++){
                    typeIds.add(parentsId[i]);
                }
                //判断是否父节点都处理了
                if(isAllNodeHandle(typeIds,processNode.getProId())){
                    //查询数据判断是否都通过
                    if(isAllNodePassed(typeIds,processNode.getProId())){
                        //都通过,更新父节点的状态
                        List<ProcessNode> processNodes = processNodeDao.selectByTypeIds(typeIds,processNode.getProId());
                        for (ProcessNode node : processNodes){
                            node.setNodeState((byte)1);
                        }
                        //批量更新
                        processNodeDao.updateByBatch(processNodes);
                        //将合并节点的所有前节点更新状态
                        ProcessNode conditionnew = new ProcessNode();
                        conditionnew.setProId(processNode.getProId());
                        conditionnew.setCurrentHandlePersonId(processNode.getCurrentHandlePersonId());
                        List<ProcessNode> nodes = processNodeDao.selectListByCondition(conditionnew);
                        for (ProcessNode node : nodes){
                            node.setNodeState((byte)1);
                        }
                        //批量更新
                        processNodeDao.updateByBatch(nodes);
                        break;
                    }else{
                        //有没有通过的
                        //结束流程
                        //更新流程为终结状态
                        endingProcess(processNode);
                        //设置当前节点为终结状态
                        processNode.setIsEndNode("1");
                        //更新
                        processNodeDao.updateByPrimaryKeySelective(processNode);
                    }
                }else{
                    //通知未处理的负责人去处理
                    List<ProcessNode> nodes =  processNodeDao.selectUnHandleNodeByTypeIds(typeIds,processNode.getProId());
                    nodes.forEach(node->{
                        noticeParentsHandle(node.getCurrentHandlePersonId());
                    });
                    throw new ProcessRuntimeException("请等待对应负责人处理！");
                }
            default:
                throw new ProcessRuntimeException(Constant.DATA_ERROR);
        }
        updateProcessNodeInfo(processNode, nodeList);
    }

    /**
     * 更新信息
     * @param processNode
     * @param nodeList
     */
    private void updateProcessNodeInfo(ProcessNode processNode, List<ProcessNode> nodeList) {
        /**
         * 更新节点信息
         */
        processNode.setNodeState((byte)1);
        processNodeDao.updateByPrimaryKeySelective(processNode);
        //获取下一个（多个）nodetypebase节点信息,并复制到下一个node信息
        List<ProcessNodeTypeBase> processNodeTypeBases = processNodeTypeBaseDao.selectByParentsId(processNode.getTypeId());
        if(CollectionUtils.isNotEmpty(processNodeTypeBases)){
            //复制信息到下一个负责人信息
            for(ProcessNodeTypeBase processNodeTypeBase : processNodeTypeBases){
                ProcessNode processNodeNew = new ProcessNode();
                BeanUtil.copyProperties(processNode,processNodeNew);
                processNodeNew.setTypeId(processNodeTypeBase.getId());
                //设置节点拆分状态
                processNodeNew = setProcessNodeBranch(processNodeNew);
                //判断下一个节点是否是结束节点
                if(processNodeTypeBase.getIsEndNode() == 1){
                    //为终节点
                    //更新流程信息
                    endingProcess(processNode);
                    //设置下一个节点为终节点
                    processNodeNew = setProcessNode(processNodeNew, "3");
                    processNodeNew.setNodeState((byte)1);
                }else{
                    //不是终节点
                    processNodeNew.setCurrentHandlePersonId(processNodeTypeBase.getCorrelationId());
                    //设置下一个节点为过程节点
                    processNodeNew = setProcessNode(processNodeNew,"2");
                }
                CopyOptions copyOptions = new CopyOptions();
                copyOptions.setIgnoreProperties("id");
                BeanUtil.copyProperties(processNodeTypeBase,processNodeNew,copyOptions);
                nodeList.add(processNodeNew);
            }
            //插入新节点
            processNodeDao.insertBatch(nodeList);
        }
    }

    /**
     * 判断节点是否都通过了
     * @param typeIds
     * @param proId
     * @return
     */
    private boolean isAllNodePassed(List<String> typeIds,String proId) {
        int allNodePassed = processNodeDao.isAllNodePassed(typeIds,proId);
        if(allNodePassed != typeIds.size()){
            return false;
        }
        return true;
    }

    /**
     * 判断节点是否都处理了
     * @param typeIds
     * @param proId 流程id
     * @return
     */
    private boolean isAllNodeHandle(List<String> typeIds,String proId) {
        int allNodeHandle = processNodeDao.isAllNodeHandle(typeIds,proId);
        if(allNodeHandle != typeIds.size()){
            return false;
        }
        return true;
    }



    /**
     * 设置节点拆分状态，（不考虑节点既是合并节点又是拆分节点的情况）
     * @param processNodeNew
     * @return
     */
    private ProcessNode setProcessNodeBranch(ProcessNode processNodeNew) {
        //获取processNodeNew子节点个数
        List<ProcessNodeTypeBase> typeBases = processNodeTypeBaseDao.selectByParentsId(processNodeNew.getTypeId());
        //processNodeNew有两个以上子节点,为拆分节点
        if(typeBases != null && typeBases.size() > 1){
            processNodeNew.setNodeBranch("1");
            return processNodeNew;
        }
        //获取processNodeNew父节点个数
        ProcessNodeTypeBase nodeTypeBase = processNodeTypeBaseDao.selectByPrimaryKey(processNodeNew.getTypeId());
        //两个以上父节点,并且只有一个子节点,为合并节点
        if(nodeTypeBase.getParentsId()!=null && nodeTypeBase.getParentsId().split(",").length > 1
                && typeBases.size() == 1){
            processNodeNew.setNodeBranch("2");
            return processNodeNew;
        }
        //其余默认为不拆分节点
        processNodeNew.setNodeBranch("0");
        return processNodeNew;
    }

    /**
     * 终结流程信息
     * @param processNode
     */
    private void endingProcess(ProcessNode processNode) {
        Process process = processDao.selectByPrimaryKey(processNode.getProId());
        process.setFileState((byte) 1);
        process.setState((byte) 0);
        processDao.updateByPrimaryKeySelective(process);
    }

    /**
     * 通知当前处理人
     * @param uid
     */
    private void noticeParentsHandle(String uid) {
        //判断当前id是否在线
        UserLoginInfo userLoginInfo = userLoginInfoDao.selectByPrimaryKey(uid);
        //在线`
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
        processNodeNew.setCreatedTime(new Date());
        return processNodeNew;
    }


    /**
     * 设置流程初始节点信息
     * @param processDto
     * @param processId 流程id
     * @param nodeType 基础节点类型 1-开始节点 2-过程节点 3-结束节点
     */
    private ProcessNode setProcessNode(ProcessDto processDto,String processId,String nodeType)  {
        //从nodeBase中根据节点类型获取开始节点名称属性
        ProcessNodeBase processNodeBase = processNodeBaseDao.selectByNodeType(nodeType);
        //属性复制
        ProcessNode processNode = new ProcessNode();
        //复制参数设置
        CopyOptions options = new CopyOptions();
        options.setIgnoreNullValue(true);
        BeanUtil.copyProperties(processNodeBase,processNode,options);
        //从nodeTypeBase中获取节点标志
        ProcessNodeTypeBase processNodeTypeBase = processNodeTypeBaseDao.selectByPrimaryKey(processDto.getNodeTypeId());
        BeanUtil.copyProperties(processNodeTypeBase,processNode,options);
        //设置节点
        processNode.setId(UUIDUtil.getUUID());
        processNode.setCreatedTime(new Date());
        User userInfo = SessionUtil.getUserInfo();
        processNode.setCurrentHandlePersonId(userInfo.getUid());
        processNode.setNodeBranch(processDto.getNodebranch());
        processNode.setNodeState((byte)0);
        processNode.setProId(processId);
        processNode.setTypeId(processDto.getNodeTypeId());
        processNode.setNodeBranch("0");
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
        if(StringUtils.isEmpty(userInfo)){
            throw new ProcessRuntimeException("获取用户信息失败，请重新登陆！");
        }
        //设置创建用户id
        process.setcreatedId(userInfo.getUid());
        process.setCreatedTime(new Date());
        process.setUpdatedTime(new Date());
        process.setFileState((byte)0);
        process.setHandleState((byte)0);
        String processId = UUIDUtil.getUUID();
        process.setId(processId);
        process.setState((byte)1);
        //获取流程类型，并设置为标题
        ProcessType processType = processTypeDao.selectByPrimaryKey(processDto.getTypeId());
        process.setTitle(userInfo.getRealName()+"-"+processType.getTypename());
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
        //更新节点
        updateNodeInfo(node);
        //通知负责人处理
        noticeHigherUps(node);
        return ResultGenerator.getSuccessResult("节点信息更新成功！");
    }

    /**
     * 获取流程类型列表
     * @return
     */
    @Override
    public Result getProcessTypeList() {
        //获取列表
        List<ProcessType> processTypes = processTypeDao.selectTypeList();
        return ResultGenerator.getSuccessResult(processTypes);
    }


}
