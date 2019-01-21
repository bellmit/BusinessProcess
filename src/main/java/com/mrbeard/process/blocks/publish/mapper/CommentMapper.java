package com.mrbeard.process.blocks.publish.mapper;

import com.mrbeard.process.blocks.publish.model.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 胡彬
 */
@Mapper
public interface CommentMapper {
    /**
     * 删除一条记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入一条记录
     * @param record
     * @return
     */
    int insert(Comment record);


    /**
     * 查找一条记录
     * @param id
     * @return
     */
    Comment selectByPrimaryKey(String id);


    /**
     * 更新一条记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Comment record);
}