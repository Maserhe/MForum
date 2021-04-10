package com.maserhe;

import com.maserhe.entity.DiscussPost;
import com.maserhe.service.DiscussPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 描述:
 *  测试 查询帖子
 * @author Maserhe
 * @create 2021-04-04 21:16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDiscussPost {

    @Autowired
    private DiscussPostService discussPostService;

    @Test
    public void Test() {
        DiscussPost post = new DiscussPost();
        post.setCommentCount(0);
        post.setUserId(String.valueOf(123));
        post.setImageUrl("http://182.92.10.243:88/maserhe/M00/00/00/rBftpWBqen2AQbK5ADCVbwL1CVI470.jpg");
        post.setCreateTime(new Date());
        post.setStatus(0);
        post.setContent("dsfadfadsfasdfasdf");
        post.setType(0);
        post.setTitle("ddjdjdjdjddjdjdjdjdj");
        post.setScore(1231);

        discussPostService.addDiscussPost(post);
    }

    @Test
    public void Test1() {
        int discussPostRows = discussPostService.findDiscussPostRows(0);
        System.out.println(discussPostRows);

    }



}
