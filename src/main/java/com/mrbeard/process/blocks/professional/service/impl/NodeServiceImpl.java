package com.mrbeard.process.blocks.professional.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.professional.dto.ProcessNodeTypeDto;
import com.mrbeard.process.blocks.professional.mapper.ProcessNodeTypeBaseMapper;
import com.mrbeard.process.blocks.professional.model.ProcessNodeTypeBase;
import com.mrbeard.process.blocks.professional.service.NodeService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
            nodeTypeBase.setNodeTypeQueue(UUIDUtil.getUUID());
            nodeTypeBase.setId(UUIDUtil.getUUID());
            if(!ToolUtil.checkParamter(nodeTypeDto.getParentsId())){
                //通过parentsId获取parentsName并设置
                //TODO
            }
            if(!ToolUtil.checkParamter(nodeTypeDto.getCorrelationId())){
                //通过correlationId获取correlationName并设置
                //TODO

            }
            nodeTypeBase.setCreatedTime(new Date());
            int flag = processNodeTypeBaseDao.insertSelective(nodeTypeBase);
            return ResultGenerator.getSuccessResult("新增节点类型成功！");
        }
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
