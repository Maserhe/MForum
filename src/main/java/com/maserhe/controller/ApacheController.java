package com.maserhe.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

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


