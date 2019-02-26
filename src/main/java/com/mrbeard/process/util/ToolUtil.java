package com.mrbeard.process.util;

import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import org.slf4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 常用工具类
 * @author 胡彬
 * @date 2017年8月22日
 */
public class ToolUtil {

    /**
     * MD5加密
     * @param plainText
     * @return
     */
    public static String Md5(String plainText) {
        String md5sString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                int i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            md5sString = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            md5sString = e.toString();
        }

        return md5sString;
    }

    /**
     * 校验必传的参数，如果有参数没传返回false，都传了返回true
     *
     * @param paramters
     * @return
     */
    public static Boolean checkParamter(Object... paramters) {
        Boolean flag = true;
        for (int i = 0; i < paramters.length; i++) {
            if (paramters[i] == null || "".equals(paramters[i].toString())) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 判断是否所有参数都存在,所有都不存在返回false ，部分存在
     * @param paramters
     * @return
     */
    public static Boolean checkIfAllParamtersExit(Object... paramters){
        int count = 0;
        for(int i = 0; i < paramters.length; i++){
            if(paramters[i] == null || "".equals(paramters[i].toString())){
                count++;
            }
        }
        if(count == paramters.length){
            return false;
        }
        return true;
    }

    /**
     * 判断是否为空
     * @param o
     * @return true 是 | false 否
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if ("".equals(o)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否不为空
     * @param o
     * @return true 是 | false 否
     */
    public static boolean isNotEmpty(Object o){
        if(isEmpty(o)){
            return false;
        }
        return true;
    }


    /**
     * 根据方法名判断是否有权限
     * @param methodName
     * @return
     */
    public static boolean isHasPermission(String methodName){
        //判断是否有修改权限
        Set<String> permissions = SessionUtil.getPermissions();
        if (permissions.contains(methodName) != true) {
            return false;
        }
        return true;
    }

}
