package com.maserhe.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *  处理用户Cookie
 * @author Maserhe
 * @create 2021-04-04 14:11
 */
public class CookieUtil {

    public static String getValue(HttpServletRequest request, String name) {
        if (request == null) {
            throw new IllegalArgumentException("参数位空");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c: cookies) {
                if (c.getName().equals(name)) {
                    return c.getValue();
                }
            }
        }
        return null;
    }
}
