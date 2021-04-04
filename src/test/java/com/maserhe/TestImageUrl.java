package com.maserhe;

import com.maserhe.entity.DiscussPost;
import com.maserhe.service.DiscussPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 描述:
 * 测试图片
 *
 * @author Maserhe
 * @create 2021-04-04 19:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestImageUrl {

    @Autowired
    private DiscussPostService discussPostService;

    @Test
    public void Test() {
        int discussPostRows = discussPostService.findDiscussPostRows(0);
        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, 0, 100);
        for (int i = 0; i < discussPosts.size(); i++) {
            System.out.println(discussPosts.get(i));
        }

        System.out.println(discussPostRows);
    }
}
