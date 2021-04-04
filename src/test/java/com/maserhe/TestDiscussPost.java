package com.maserhe;

import com.maserhe.entity.DiscussPost;
import com.maserhe.service.DiscussPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        DiscussPost post = discussPostService.findDiscussPostById(109);
        System.out.println(post);
    }

}
