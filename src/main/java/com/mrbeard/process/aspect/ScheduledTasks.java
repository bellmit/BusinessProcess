package com.mrbeard.process.aspect;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import com.mrbeard.process.aspect.mapper.InteractiveLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author 胡彬
 * @Date 2018/12/4 11:10
 * 定时任务
 **/
@Component
public class ScheduledTasks {

    static Logger logger = LoggerFactory.getLogger(Scheduled.class);

    @Resource
    private InteractiveLogMapper interactiveLogDao;

    /**
     * 每天凌晨4点删除过期的交互日志
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void deleteExpiredInteractiveLog(){
        int deleteRows = interactiveLogDao.deleteExpired();
        logger.info(">>> 删除 " + deleteRows + " 条过期交互日志！");
    }

    /**
     * 测试定时任务
     */
//    @Scheduled(cron = "*/5 * * * * ?")
//    public void test(){
//        logger.info("执行时间"+DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
//    }
}
