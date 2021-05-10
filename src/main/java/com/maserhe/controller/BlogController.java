package com.maserhe.controller;

import com.maserhe.entity.Comment;
import com.maserhe.entity.DiscussPost;
import com.maserhe.entity.Page;
import com.maserhe.entity.User;
import com.maserhe.service.CommentService;
import com.maserhe.service.DiscussPostService;
import com.maserhe.service.RedisService;
import com.maserhe.service.UserService;
import com.maserhe.util.FileClient;
import com.maserhe.util.HostHolder;
import com.maserhe.util.SensitiveFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisService redisService;

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

    @GetMapping("/site/detail/{discussPostId}")
    public String findDiscussPost(@PathVariable int discussPostId, Model model, Page page) {

        // 根据id 查询到相应的 帖子
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        // 设置总数
        int commentsCount = commentService.findCommentsCount(discussPostId);
        discussPost.setCommentCount(commentsCount);
        // 将帖子放入 discussPost中
        model.addAttribute("discussPost", discussPost);
        User user = userService.findUserById(Integer.valueOf(discussPost.getUserId()));
        model.addAttribute("discussUser", user);

        // 设置分页功能
        page.setRows(commentsCount);
        page.setPath("/site/detail/" + discussPostId);
        // 查询 所有的 评论
        page.setLimit(5);
        List<Comment> comments = commentService.findComments(discussPostId, page.getOffset(), page.getLimit());
        // 查询所有User
        List<Map<String, Object>> commentList = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            int userId = comments.get(i).getUserId();
            User commentUser = userService.findUserById(userId);
            if (commentUser != null) {
                Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("user", commentUser);
                tempMap.put("comment", comments.get(i));
                commentList.add(tempMap);
            }
        }

        model.addAttribute("commentList", commentList);
        // 判断用户是否评论过
        boolean doStar = commentService.isDoStar(hostHolder.getUser().getId(), discussPostId);
        model.addAttribute("hasStar", doStar);

        // 查询是否点赞
        long count = redisService.count(discussPostId);
        model.addAttribute("count", count);

        return "/site/discuss-detail";
    }


    @PostMapping("/site/addComment/{discussPostId}")
    public String addComment(@PathVariable int discussPostId, String score, String article) {
        // 获取当前User
        User user = hostHolder.getUser();
        // 设置撰写评论
        Comment comment = new Comment();
        if (score != null) {
            comment.setStar(Integer.valueOf(score));
            comment.setStatus(1);
        }

        comment.setContent(article);
        comment.setUserId(user.getId());
        comment.setCreateTime(new Date());
        comment.setEntityId(discussPostId);
        commentService.addComment(comment);

        return "redirect:/site/detail/"+discussPostId;
    }
}
