package com.mrbeard.process.util;

import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import org.slf4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 常用工具类
 * @author 胡彬
 * @date 2017年8月22日
 */
public class ToolUtil {

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
            if (paramters[i] == null || paramters[i].toString().equals("")) {
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

    private static Pattern numberPattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");

    /**
     * 判断是否为数字格式不限制位数
     *
     * @param o
     * @return
     */
    public static boolean isNumber(Object o) {
        return numberPattern.matcher(String.valueOf(o)).matches();
    }

    private static Pattern integerNumberPattern = Pattern.compile("[0-9]*");


    /**
     * 判断是否为整数
     *
     * @param o
     * @return
     */
    public static boolean isIntegerNumber(Object o) {
        return integerNumberPattern.matcher(String.valueOf(o)).matches();
    }


    /**
     * 将list分组成map
     * 分组过程中可以根据isRemoveAttr判断是否需要移除明细表中的某个属性
     * 如果有多个属性，用逗号分隔
     *
     * @param list
     * @param isRemoveAttr   判断是否需要移除明细表中的某个属性
     * @param groupName      分组的名称，多个可以用逗号分隔
     * @param removeAttrName 需要移除的属性，如果isRemoveAttr为true，且要移除的属性和分组的相同，传null就可以
     * @return
     */
    public static Map<String, List<Map<String, Object>>> listGroupBy(List<Map<String, Object>> list, String groupName, boolean isRemoveAttr, String removeAttrName) {
        String[] groupNames = groupName.split("\\,");
        //LinkedHashMap保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的.
        // 用此方法，如果需要有序，则需要数据库里面排序，或分组后，程序再次排序
        Map<String, List<Map<String, Object>>> groupMap = new LinkedHashMap<>();
        if (list != null && list.size() > 0) {
            //对查询结果进行再分组
            //循环查询结果
            for (int i = 0; i < list.size(); i++) {
                //单条结果
                Map<String, Object> oneMap = list.get(i);
                //判断要分组的属性如果不存在，则继续下一个
                if (isEmpty(oneMap.get(groupNames[0]))) {
                    continue;
                }
                String key = String.valueOf(oneMap.get(groupNames[0]));
                for (int j = 1; j < groupNames.length; j++) {
                    String s = String.valueOf(oneMap.get(groupNames[j]));
                    if("".equals(s)){
                        s="null";
                    }
                    key += "^" + s;
                }
                //如果本条结果要分组的条件作为map的key去查询，在resultMap中已经存在了，则直接将本条记录增加到resultMap中key对应的list中
                if (groupMap.containsKey(key)) {
                    groupMap.get(key).add(oneMap);
                } else {
                    //如果本条结果还没有出现在resultMap中，则将它put到resultMap中
                    List<Map<String, Object>> tempList = new ArrayList<>();
                    tempList.add(oneMap);
                    groupMap.put(key, tempList);
                    /*for(int j=0;j<groupNames.length;j++){
                        groupMap.put(groupNames[j].toString(),oneMap.get(groupNames[j]));
                    }*/
                }

                if (isRemoveAttr) {
                    if (isEmpty(removeAttrName)) {
                        removeAttrName = groupName;
                    }
                    String[] removeAttrNames = removeAttrName.split("\\,");
                    for (String name : removeAttrNames) {
                        oneMap.remove(name);//前台数据不需要这一列
                    }
                }

            }
        }
        return groupMap;
    }

    /**
     * 将传入的value按照pattern样式进行格式化输出返回
     *
     * @param value
     * @param pattern
     * @return
     */
    public static double numberFormat(Object value, String pattern) {
        try {
            DecimalFormat dFormat = new DecimalFormat(pattern);
            return Double.valueOf(dFormat.format(value));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ToolUtil.numberFormat异常参数:【" + value + "】,【" + pattern + "】");
            throw e;
        }
    }

    public static Result getMessage(Exception e, Logger logger) {
        try {
            if (e instanceof com.mrbeard.process.exception.ProcessRuntimeException) {
                //系统自己主动抛出的友好提示，把提示信息返回
                //正常提示的信息，以warn级别记录
                /*String expMsg = "系统提示异常信息，\n操作账号id："+WebUtil.getPropertyFromSession("employeeid")+
						"，\n用户名："+WebUtil.getPropertyFromSession("employeeguid")+
						"，\n所属筛查中心："+WebUtil.getPropertyFromSession("centername")+
						"，\n异常请求IP："+request.getRemoteAddr()+"，\n访问端口："+request.getRemotePort()+
						"，\n请求URI："+request.getRequestURI()+"\n异常请求参数："+WebUtil.getParameterMap(request);*/
//				logger.warn(expMsg,e);
                logger.warn(e.getMessage());
                return ResultGenerator.getErrorResult(e.getMessage());
            } else {
                //未捕捉的异常，返回统一信息，提升用户浏览体验，同时将真正的异常信息记录到日志
				/*String expMsg = "系统异常错误信息，\n操作账号id："+WebUtil.getPropertyFromSession("employeeid")+
						"，\n用户名："+WebUtil.getPropertyFromSession("employeeguid")+
						"，\n所属筛查中心："+WebUtil.getPropertyFromSession("centername")+
						"，\n错误请求IP："+request.getRemoteAddr()+"，访问端口："+request.getRemotePort()
						+"，\n请求URI："+request.getRequestURI()+"，\n异常请求参数："+WebUtil.getParameterMap(request);*/
//				logger.error(expMsg,e);
                logger.error(e.getMessage());
                return ResultGenerator.getErrorResult(e.getMessage());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return ResultGenerator.getErrorResult(e1.getMessage());
        }
    }
}
