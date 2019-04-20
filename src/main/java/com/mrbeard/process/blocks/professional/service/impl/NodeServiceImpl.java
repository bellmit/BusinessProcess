package com.mrbeard.process.blocks.professional.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.blocks.professional.mapper.ProcessNodeTypeBaseMapper;
import com.mrbeard.process.blocks.professional.model.ProcessNodeTypeBase;
import com.mrbeard.process.blocks.professional.service.NodeService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @ClassName NodeServiceImpl
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/4/18 14:18
 * @Version 1.0
 **/
@Service
public class NodeServiceImpl  implements NodeService {

    @Resource
    ProcessNodeTypeBaseMapper processNodeTypeBaseDao;
    @Resource
    UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 配置节点类型
     * @param nodeTypeDto
     * @return
     */
    @Override
    public Result postNodeType(ProcessNodeTypeDto nodeTypeDto) {
        //拷贝属性
        ProcessNodeTypeBase nodeTypeBase = new ProcessNodeTypeBase();
        BeanUtil.copyProperties(nodeTypeDto,nodeTypeBase);
        if(ToolUtil.checkParamter(nodeTypeDto.getId())){
            //修改
            if(ToolUtil.checkParamter(nodeTypeDto.getName())){
                processNodeTypeBaseDao.updateByPrimaryKeySelective(nodeTypeBase);
                return ResultGenerator.getSuccessResult("修改节点类型成功！");
            }else{
                //删除
                //判断是否有下一个节点使用，如有则不允许删除
                List<ProcessNodeTypeBase> bases = processNodeTypeBaseDao.selectByParentsId(nodeTypeDto.getId());
                if(bases != null && bases.size() > 0){
                    return ResultGenerator.getErrorResult("该节点已被作为父节点，请先删除所有子节点后重试！");
                }
                processNodeTypeBaseDao.deleteByPrimaryKey(nodeTypeDto.getId());
                return ResultGenerator.getSuccessResult("删除节点类型成功！");
            }
        }else{
            //新增
            if(!ToolUtil.checkParamter(nodeTypeDto.getName(), nodeTypeDto.getIsBeginNode(),
                    nodeTypeDto.getIsEndNode(),nodeTypeDto.getProcessTypeId())){
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            //判断nodeTypeQueue是否为空，不为空则新增
            if(StringUtils.isEmpty(nodeTypeDto.getNodeTypeQueue())){
                nodeTypeBase.setNodeTypeQueue(UUIDUtil.getUUID());
            }else{
                nodeTypeBase.setNodeTypeQueue(nodeTypeDto.getNodeTypeQueue());
            }
            nodeTypeBase.setId(UUIDUtil.getUUID());
            if(ToolUtil.checkParamter(nodeTypeDto.getParentsId())){
                //通过parentsId获取parentsName并设置
                String[] parentIdArray = nodeTypeDto.getParentsId().split(",");
                //设置数据到对应的map
                List<ProcessNodeTypeBase>  parentsMap = new ArrayList<>();
                for(String par : parentIdArray){
                    ProcessNodeTypeBase nodeType = new ProcessNodeTypeBase();
                    nodeType.setId(par);
                    parentsMap.add(nodeType);
                }
                //根据parentsId查询数据
                List<ProcessNodeTypeBase> bases = processNodeTypeBaseDao.selectListByConditionList(parentsMap);
                //获取parentsid、parentsname集合
                String parentsIds = getListCollection(bases,"id");
                String parentsNames = getListCollection(bases,"name");
                nodeTypeBase.setParentsId(parentsIds);
                nodeTypeBase.setParentsName(parentsNames);
            }
            if(ToolUtil.checkParamter(nodeTypeDto.getCorrelationId())){
                //通过correlationId获取correlationName并设置
                User user = userService.selectUserById(nodeTypeDto.getCorrelationId());
                nodeTypeBase.setCorrelationName(user.getRealName());
            }
            nodeTypeBase.setCreatedTime(new Date());
            int flag = processNodeTypeBaseDao.insertSelective(nodeTypeBase);
            return ResultGenerator.getSuccessResult("新增节点类型成功！");
        }
    }

    /**
     * 根据集合获取对应的拼接字符串
     * @param bases
     * @param type
     * @return
     */
    private String getListCollection(List<ProcessNodeTypeBase> bases, String type) {
        if(CollectionUtils.isEmpty(bases)){
            return null;
        }
        StringBuilder str = new StringBuilder();
        String result = null;
        for(ProcessNodeTypeBase nodeTypeBase : bases){
            //通过反射获取属性值
            Class<? extends ProcessNodeTypeBase> clazz = nodeTypeBase.getClass();
            try {
                Field field = clazz.getDeclaredField(type);
                field.setAccessible(true);
                Object val = field.get(nodeTypeBase);
                str.append(val+",");
                System.out.println(val);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                throw new ProcessRuntimeException("属性类型错误！");
            }
        }
        result = str.toString();
        if(StringUtils.isNotEmpty(result)){
            result = StringUtils.removeEnd(result,",");
        }
        return result;
    }

    /**
     * 通过processTypeId获取列表
     * @param processTypeId
     * @return
     */
    @Override
    public Result getProcessNodeTypeList(String processTypeId,Integer pageNum,Integer pageSize) {
        ProcessNodeTypeDto nodeTypeBase = new ProcessNodeTypeDto();
        nodeTypeBase.setProcessTypeId(processTypeId);
        nodeTypeBase.setIsBeginNode((byte)1);
        nodeTypeBase.setSortName("createdTime");
        nodeTypeBase.setSortCase("desc");
        PageHelper.startPage(pageNum,pageSize);
        List<ProcessNodeTypeBase> nodeTypeBaseList = processNodeTypeBaseDao.selectListByCondition(nodeTypeBase);
        PageInfo<ProcessNodeTypeBase> pageInfo = new PageInfo<>(nodeTypeBaseList);
        return ResultGenerator.getSuccessResult(pageInfo);
    }

    /**
     * 根据nodeTypeQueue获取nodeTypeList
     * @param nodeTypeQueue
     * @return
     */
    @Override
    public Result getNodeTypeListByQueue(String nodeTypeQueue) {
        ProcessNodeTypeDto nodeTypeDto = new ProcessNodeTypeDto();
        nodeTypeDto.setNodeTypeQueue(nodeTypeQueue);
        nodeTypeDto.setSortName("isBeginNode");
        nodeTypeDto.setSortCase("desc");
        List<ProcessNodeTypeBase> nodeTypeBaseList = processNodeTypeBaseDao.selectListByCondition(nodeTypeDto);
        return ResultGenerator.getSuccessResult(nodeTypeBaseList);
    }
}
