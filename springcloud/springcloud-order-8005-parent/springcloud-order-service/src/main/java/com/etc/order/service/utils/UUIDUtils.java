package com.etc.order.service.utils;


import java.util.UUID;

/**
 * UUID工具类
 * @author Yuxiang Liu
 * */
public class UUIDUtils {

    public UUIDUtils() {
    }

    public static synchronized String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().trim().replace("-", "");
    }

    public static String getUUID(int len){
        if(0 >= len) {
            return null;
        }
        String uuid = getUUID();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < len; i++) {
            str.append(uuid.charAt(i));
        }
        return str.toString();
    }


}
