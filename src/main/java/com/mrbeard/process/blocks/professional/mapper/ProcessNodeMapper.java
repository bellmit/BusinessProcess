package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.ProcessNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessNodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessNode record);

    int insertSelective(ProcessNode record);

    ProcessNode selectByPrimaryKey(String id);

    ProcessNode selectByCondition(ProcessNode processNode);

    int updateByPrimaryKeySelective(ProcessNode record);

    int updateByPrimaryKey(ProcessNode record);

    /**
     * 批量插入
     * @param nodeList
     */
    int insertBatch(List<ProcessNode> nodeList);

    /**
     * 判断节点是否都处理了
     * @param typeIds
     * @return
     */
    int isAllNodeHandle(List<String> typeIds);

    /**
     * 判断节点是否都通过了
     * @param typeIds
     * @return
     */
    int isAllNodePassed(List<String> typeIds);

    /**
     * 获取未处理节点信息
     * @param typeIds
     * @return
     */
    List<ProcessNode> selectUnHandleNodeByTypeIds(List<String> typeIds);

    /**
     * 判断是否已经有节点是下一个处理人未处理的数据
     * @param currentHandlePersonId
     * @return
     */
    int isExitCurrentHandlePersonId(String currentHandlePersonId);
}