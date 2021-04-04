package com.maserhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 15:08
 */
@Controller
public class ApacheController {

    @RequestMapping("/test")
    public String getTest() {

        return "index";
    }

}


