package com.mrbeard.process.blocks.publish.mapper;

import com.mrbeard.process.blocks.publish.model.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 胡彬
 */
@Mapper
public interface BlogMapper {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Blog record);

    /**
     * 查找
     * @param id
     * @return
     */
    Blog selectByPrimaryKey(String id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Blog record);

    /**
     * 按时间倒序查找bolg
     * @return
     */
    List<Blog> getBlogList();
}