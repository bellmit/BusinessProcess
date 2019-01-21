//package com.mrbeard.zone.util;
//
//import com.mrbeard.zone.common.Constant;
//import com.mrbeard.zone.exception.ProcessRuntimeException;
//import com.mrbeard.zone.result.ResultGenerator;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.util.Iterator;
//
///**
// * @Author 胡彬
// * @Date 2018/12/6 14:52
// * 图片工具类
// **/
//public class PictureUtil {
//
//    private static Logger logger = LoggerFactory.getLogger(PictureUtil.class);
//
//    /**
//     * 上传文件
//     */
//    public static String uploadPicture(HttpServletRequest request) throws ProcessRuntimeException {
//        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        //检查form中是否有enctype="multipart/form-data"
//        if (!multipartResolver.isMultipart(request)) {
//            logger.error("uploadPicture ================>：提交方式错误");
//            //提交方式错误
//            throw new ProcessRuntimeException(Constant.ERROR_IN_SUBMISSION_MODE);
//        }
//        //将request变成多部分request
//        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//        //获取multiRequest 中所有的文件名
//        Iterator iter = multiRequest.getFileNames();
//        if (!iter.hasNext()) {
//            //文件不存在
//            logger.error("uploadPicture ================>：文件不存在");
//            throw new ProcessRuntimeException(Constant.FILE_UNEXIT);
//        }
//        String path = null;
//        while (iter.hasNext()) {
//            //一次遍历所有文件
//            MultipartFile file = multiRequest.getFile(iter.next().toString());
//            if (file != null) {
//                String path0 = wFileService.getuploadPath(request.getParameter("labitem"), request.getParameter("deptid"));
//                if (path0 == null || "".equals(path0) || !(new File(path0)).exists()) {
//                    log.error("FileUpload中：上传路径不存在");
//                    //return resModel.warn("上传路径不存在");
//                    return resModel.fail(AcmeErr.ACME_ERR_UPLOAD_PATH_NOT_EXIST);  //上传路径不存在
//                }
//                if (file.getOriginalFilename().contains("/") || file.getOriginalFilename().contains("\\") || file.getOriginalFilename().contains("C:") || file.getOriginalFilename().contains("c:")) {
//                    log.error("FileUpload中：非法的访问");
//                    //return resModel.warn("非法的访问");
//                    return resModel.fail(AcmeErr.ACME_ERR_ILLEGAL_ACCESS);  //非法的访问
//                }
//                path = "";
//                if (path0.charAt(path0.length() - 1) != '/') {
//                    path0 += "\\";
//                }
//                if (request.getParameter("processid") != null) {
//                    path = path0 + request.getParameter("processid") + file.getOriginalFilename();
//                } else if (request.getParameter("timetoken") != null) {
//                    path = path0 + request.getParameter("timetoken") + file.getOriginalFilename();
//                } else {
//                    path = path0 + file.getOriginalFilename();
//                }
//
//                path = path.replaceAll("\\.{2}", "");
//                //上传
//                file.transferTo(new File(path));
//            } else {
//                log.error("FileUpload中：文件不存在");
//                //return resModel.warn("文件不存在");
//                return resModel.fail(AcmeErr.ACME_ERR_FILE_NOT_EXIST);  //文件不存在
//            }
//
//        }
//        return resModel.success(path);
//
//    }
//
//
//}
