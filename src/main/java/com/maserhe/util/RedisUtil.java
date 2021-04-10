package com.maserhe.util;/**
 * 描述:
 *    redis的工具类
 * @author Maserhe
 * @create 2021-04-10 9:42
 */

public class RedisUtil {

    /**
     * 点赞模块的 前缀
     */
    public static final String PREFIX = "TestBlog:like:";

    /**
     * 登陆验证码前缀
     */
    public static final String PREFIX_KAPTCHA = "TestBlog:kaptcha:";

    public static final String PREFIX_TICKET = "TestBlog:ticket:";

    public static final String PREFIX_USER = "TestBlog:user:";

    public static String getKey(int discussPostId) {
        return PREFIX + discussPostId;
    }

    public static String getKaptchaKey(String owner) {
       return PREFIX_KAPTCHA + owner;
    }

    public static String getTicketKey(String ticket) { return PREFIX_TICKET + ticket; }

    public static String getUserKey(int userid) { return PREFIX_USER + userid; }
}
