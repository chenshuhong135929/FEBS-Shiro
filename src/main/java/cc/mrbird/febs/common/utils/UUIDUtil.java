package cc.mrbird.febs.common.utils;

import java.util.UUID;

/**
 * @Auther: liaotao
 * @Date: 2018/11/21 16:07
 * @Description:
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().toUpperCase();
    }
}
