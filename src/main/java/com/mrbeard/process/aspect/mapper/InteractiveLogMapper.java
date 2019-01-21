package com.mrbeard.process.aspect.mapper;

import com.mrbeard.process.aspect.model.InteractiveLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 胡彬
 * 交互日志mapper
 */
@Mapper
public interface InteractiveLogMapper {

    /**
     * 插入一条数据
     * @param interactiveLog
     * @return
     */
    int insert(InteractiveLog interactiveLog);

    /**
     * 删除每天的交互的日志
     * @return
     */
    int deleteExpired();
}
