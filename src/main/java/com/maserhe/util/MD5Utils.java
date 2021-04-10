package com.maserhe.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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


    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 25);
        System.out.println(getJSONString(0, "ok", map));
    }

}
