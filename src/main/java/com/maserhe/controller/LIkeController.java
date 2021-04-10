package com.maserhe.controller;

import com.maserhe.service.RedisService;
import com.maserhe.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 点赞控制器
 *
 * @author Maserhe
 * @create 2021-04-10 10:51
 */
@Controller
public class LIkeController {

    @Autowired
    private RedisService redisService;

    // 这里是一个局部刷新的ajax 请求
    @PostMapping("/like")
    @ResponseBody
    public String like(int userId, int discussPostId){

        boolean like = redisService.like(discussPostId, userId);
        long count = redisService.count(discussPostId);
        Map<String,Object> map = new HashMap<>();
        map.put("flag", like);
        map.put("count", count);
        return MD5Utils.getJSONString(0,null,map);
    }

}
