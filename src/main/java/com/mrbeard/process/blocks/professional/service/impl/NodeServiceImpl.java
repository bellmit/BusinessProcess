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
     * 获取流程节点类型列表
     * @return
     */
    @Override
    public Result getProcessNodeTypeList() {
        //获取所有开始节点列表
        List<ProcessNodeTypeBase> nodeTypes = processNodeTypeBaseDao.selectBeginNodeList();
        return ResultGenerator.getSuccessResult(nodeTypes);
    }

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
                processNodeTypeBaseDao.deleteByPrimaryKey(nodeTypeDto.getId());
                return ResultGenerator.getSuccessResult("删除节点类型成功！");
            }
        }else{
            //新增
            if(!ToolUtil.checkParamter(nodeTypeDto.getName(),nodeTypeDto.getCorrelationId(),
                    nodeTypeDto.getIsBeginNode(),nodeTypeDto.getIsEndNode(),nodeTypeDto.getProcessTypeId())){
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            nodeTypeBase.setId(UUIDUtil.getUUID());
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
        ProcessNodeTypeBase nodeTypeBase = new ProcessNodeTypeBase();
        nodeTypeBase.setProcessTypeId(processTypeId);
        nodeTypeBase.setIsBeginNode((byte)1);
        PageHelper.startPage(pageNum,pageSize);
        List<ProcessNodeTypeBase> nodeTypeBaseList = processNodeTypeBaseDao.selectListByCondition(nodeTypeBase);
        PageInfo<ProcessNodeTypeBase> pageInfo = new PageInfo<>(nodeTypeBaseList);
        return ResultGenerator.getSuccessResult(pageInfo);
    }
}
