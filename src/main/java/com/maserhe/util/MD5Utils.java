package com.maserhe.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 描述:
 * 处理密码相关的工具类
 *
 * @author Maserhe
 * @create 2021-04-03 16:35
 */
public class MD5Utils {

    // 生成随机字符串。
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String md5(String key) {
        if (StringUtils.isBlank(key)) return null;
        return DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
    }
}
