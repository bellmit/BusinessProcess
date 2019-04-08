package com.mrbeard.process.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

/**
 * @ClassName SendMessageUtil
 * @Description 用于发送短信工具类
 * @Author Mrbeard
 * @Date 2019/4/8 16:33
 * @Version 1.0
 **/
public class SendMessageUtil {
    /**
     * 短信应用SDK AppID  1400开头
     */
    private static final int appid = 1400083813;

    /**
     * 短信应用SDK AppKey
     */
    private static final String appkey = "286d90d6064e6c736cf0a7b66abe2ecb";

    /**
     * 短信模板ID，需要在短信应用中申请
     */
    private static final int templateId = 309730;
    /**
     * 签名
     */
    private static final String smsSign = "胡子先生";

    /**
     * 国家码，如 86 为中国
     */
    private static final String nationCode = "86";

    /**
     * 发送短信
     *
     * @param phoneNumber 手机号码
     * @param params      固定大小为2的字符串数组 例如：{"5678", "4"},第一个字符串为验证码，第二个字符串为有效时间（可根据自己需要设置）
     * @return 返回样例： {"result":0,"errmsg":"OK","ext":"","sid":"18:972aeec1e71f4b05b69ab724ed4b8184","fee":1}
     */
    public static String sendMessage(String phoneNumber, String[] params) {
        //返回结果
        String resultString = "";
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam(nationCode, phoneNumber, templateId, params, smsSign, "", "");
            resultString = result.toString();
            return resultString;
        } catch (Exception e) {
            e.printStackTrace();
            return "发送短信失败！";
        }
    }
}
