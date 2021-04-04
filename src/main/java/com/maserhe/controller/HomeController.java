package com.maserhe.controller;

import com.maserhe.entity.DiscussPost;
import com.maserhe.entity.Page;
import com.maserhe.service.DiscussPostService;
import com.maserhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 14:50
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private DiscussPostService discussPostService;

    /**
     * 起始页面的跳转。
     * @return
     */

    @GetMapping( "/site/login.html")
    public String gotoLogin() {
        return "/site/login";
    }

    @GetMapping( "/site/letter.html")
    public String gotoLetter() {
        return "/site/letter";
    }
    @GetMapping("/site/register.html")
    public String gotoRegister() {
        return "/site/register";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index.html");

        // 查询数据, 首页显示 10条数据
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());

        List<Map<String, Object>> discussPosts = new ArrayList<>();

        if (list != null) {
            for (DiscussPost post: list) {
                Map<String, Object> map = new HashMap<>();
                map.put("user", userService.findUserById(Integer.valueOf(post.getUserId())));
                map.put("post", post);
                discussPosts.add(map);
            }
        }
        // 将数据放入上下文。
        model.addAttribute("discussPosts", discussPosts);
        // 这里不需要 将 page 放入Model中
        return "/index";
    }

}
