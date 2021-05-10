package com.maserhe.controller;

import com.maserhe.entity.DiscussPost;
import com.maserhe.mapper.DiscussPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 15:08
 */
@Controller
public class ApacheController {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @RequestMapping("/test")
    public String getTest() {

        List<DiscussPost> allDiscussPost = discussPostMapper.getAllDiscussPost(0, 1, 1000);

        for (int i = 0; i < allDiscussPost.size(); i++) {
            System.out.println(allDiscussPost.get(i));
        }


        return "index";
    }

}


