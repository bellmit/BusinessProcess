package com.mrbeard.process.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

/**
 * @Author 胡彬
 * @Date 2018/12/6 17:32
 * 文件路径工具类
 **/
public class FilePathUtil {

    private static Logger logger = LoggerFactory.getLogger(FilePathUtil.class);

    /**
     * 获取服务器根目录路径
     * @return
     */
    public static String getWebRootContextPath() throws FileNotFoundException {
        //路径  E:\SpringBoot_WorkSpace\MrbeardZone
        String userPath = System.getProperty("user.dir");
        logger.info("获取到的用户根目录：System.getProperty(\"user.dir\")=======>"+userPath);
        //获取项目根目录
        return userPath;
    }
}
