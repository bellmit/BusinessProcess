package com.mrbeard.process.blocks.config.mapper;

import com.mrbeard.process.blocks.config.model.FileUrl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mrbeard
 */
@Mapper
public interface FileUrlConfigMapper {
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
    int insert(FileUrl record);

    /**
     * 查找一条
     * @param id
     * @return
     */
    FileUrl selectByPrimaryKey(String id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(FileUrl record);

    /**
     * 通过type查找是否有数据
     * @param type
     * @return
     */
    FileUrl selectByType(String type);


    /**
     * 列表查询
     * @return
     */
    List<FileUrl> selectList();
}