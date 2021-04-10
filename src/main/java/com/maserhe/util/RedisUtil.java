package com.maserhe.util;/**
 * 描述:
 *    redis的工具类
 * @author Maserhe
 * @create 2021-04-10 9:42
 */

public class RedisUtil {

    public static final String PREFIX = "TestBlog:like:";

    public static String getKey(int discussPostId) {
        return PREFIX + discussPostId;
    }
}
