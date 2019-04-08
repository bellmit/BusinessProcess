package com.mrbeard.process.blocks.professional.mapper;

import com.mrbeard.process.blocks.professional.model.Process;
import com.mrbeard.process.blocks.professional.model.ProcessNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProcessNodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProcessNode record);

    int insertSelective(ProcessNode record);

    ProcessNode selectByPrimaryKey(String id);

    ProcessNode selectByCondition(ProcessNode processNode);

    List<ProcessNode> selectListByCondition(ProcessNode processNode);

    int updateByPrimaryKeySelective(ProcessNode record);

    int updateByPrimaryKey(ProcessNode record);

    /**
     * 批量插入
     * @param nodeList
     * @return
     */
    int insertBatch(List<ProcessNode> nodeList);

    /**
     * 判断节点是否都处理了
     * @param typeIds
     * @param proId
     * @return
     */
    int isAllNodeHandle(@Param("typeIds") List<String> typeIds, @Param("proId") String proId);

    /**
     * 判断节点是否都通过了
     * @param typeIds
     * @param proId
     * @return
     */
    int isAllNodePassed(@Param("typeIds") List<String> typeIds, @Param("proId") String proId);

    /**
     * 获取未处理节点信息
     * @param typeIds
     * @param proId
     * @return
     */
    List<ProcessNode> selectUnHandleNodeByTypeIds(@Param("typeIds") List<String> typeIds, @Param("proId") String proId);

    /**
     * 通过typeIds获取所有node
     * @param typeIds
     * @param proId
     * @return
     */
    List<ProcessNode> selectByTypeIds(@Param("typeIds") List<String> typeIds, @Param("proId") String proId);


    /**
     * 批量更新
     * @param processNodes
     * @return
     */
    int updateByBatch(List<ProcessNode> processNodes);
}