package com.mrbeard.process.aspect;

import com.mrbeard.process.aspect.mapper.InteractiveLogMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author 胡彬
 * @Date 2018/12/4 11:10
 * 定时任务
 **/
@Component
public class ScheduledTasks {
    private static Logger logger = LogManager.getLogger(ScheduledTasks.class);

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
}
