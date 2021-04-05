package com.maserhe.controller;

import com.maserhe.entity.DiscussPost;
import com.maserhe.entity.User;
import com.maserhe.service.DiscussPostService;
import com.maserhe.util.FileClient;
import com.maserhe.util.HostHolder;
import com.maserhe.util.SensitiveFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * 描述: 写帖子
 *
 * @author Maserhe
 * @create 2021-04-05 12:40
 */
@Controller
public class BlogController {

    @Autowired
    private FileClient fileClient;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private HostHolder hostHolder;

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    /**
     * 实现撰写帖子的 功能
     * @param title
     * @param article
     * @param uploadImage
     * @return
     */
    @PostMapping("/index/writeBlog")
    public String writeBlog(String title, String article, @RequestParam(value = "uploadImage") MultipartFile uploadImage) {

        // 过滤敏感词
        title = sensitiveFilter.filter(title);
        article = sensitiveFilter.filter(article);
        try {
            String path = fileClient.uploadFile(uploadImage);
            // 获取用户
            User user = hostHolder.getUser();
            // 撰写博客
            DiscussPost discussPost = new DiscussPost();
            discussPost.setUserId(String.valueOf(user.getId()));
            discussPost.setTitle(title);

            discussPost.setScore(0);
            discussPost.setContent(article);
            discussPost.setType(0);
            discussPost.setCommentCount(0);
            discussPost.setStatus(0);
            discussPost.setImageUrl(path);
            discussPost.setCreateTime(new Date());

            discussPostService.addDiscussPost(discussPost);

        } catch (IOException e) {
            logger.error("文件上传出项错误");
        }

        return "redirect:/index.html";
    }

}
