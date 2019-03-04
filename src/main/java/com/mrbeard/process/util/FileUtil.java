package com.mrbeard.process.util;

import com.mrbeard.process.exception.ProcessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author 胡彬
 * @Date 2018/12/6 17:32
 * 文件路径工具类
 **/
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取服务器根目录路径
     *
     * @return
     */
    public static String getWebRootContextPath() throws FileNotFoundException {
        //路径  E:\SpringBoot_WorkSpace\MrbeardZone
        String userPath = System.getProperty("user.dir");
        logger.info("获取到的用户根目录：System.getProperty(\"user.dir\")=======>" + userPath);
        //获取项目根目录
        return userPath;
    }

    /**
     * 下载文件工具类
     * @param fileName 文件名
     * @param filePath 文件路径
     * @throws ProcessRuntimeException
     */
    public static void downloadFile(String filePath, String fileName) throws ProcessRuntimeException {
        //下载开始
        try {
            File downFiles = new File(filePath);
            FileInputStream fis = null;
            byte[] data = new byte[(int) downFiles.length()];
            fis.close();
            HttpServletResponse response = WebUtil.getResponse();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);
            response.setContentType("application/octet-stream");
            OutputStream fos = response.getOutputStream();
            fos.write(data);
            fis.close();
            fos.close();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        }
    }
}
