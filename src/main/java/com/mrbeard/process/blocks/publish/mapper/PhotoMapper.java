package com.mrbeard.process.blocks.publish.mapper;

import com.mrbeard.process.blocks.publish.model.Photo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 胡彬
 */
@Mapper
public interface PhotoMapper {
    /**
     * 删除一条记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     *  插入一条记录
     * @param record
     * @return
     */
    int insert(Photo record);

    /**
     * 查找一条记录
     * @param id
     * @return
     */
    Photo selectByPrimaryKey(String id);

    /**
     * 更新一条记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Photo record);
}