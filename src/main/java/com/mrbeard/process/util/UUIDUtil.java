package com.mrbeard.process.util;

import java.util.UUID;

/**
 * @author 胡彬
 * @Date: 2018/10/10 17:26
 * @Description:
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
