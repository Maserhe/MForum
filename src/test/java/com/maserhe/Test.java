package com.maserhe;

import com.google.code.kaptcha.Producer;
import com.maserhe.util.MailClient;
import com.maserhe.entity.DiscussPost;
import com.maserhe.entity.User;
import com.maserhe.mapper.DiscussPostMapper;
import com.maserhe.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 17:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private Producer producer;

    @org.junit.Test
    public void contextLoads() {
        User user = userMapper.selectById(11);
        System.out.println(user);

        User user1 = userMapper.selectByName("nowcoder11");
        System.out.println(user1);

        User user2 = userMapper.selectByEmail("nowcoder11@sina.com");
        System.out.println(user2);

        userMapper.updateHeader(11, "http://images.nowcoder.com/head/12t.png");
        userMapper.updateStatus(11, 0);
        userMapper.updatePassword(11,"123456");

        User user3 = new User();
        user3.setEmail("tesdfadft@qq.com");
        user3.setUsername("dfasdfsdfasdfasdf");
        user3.setCreateTime(new Date());
        userMapper.insertUser(user3);
    }

    @org.junit.Test
    public void context() {
        int count = discussPostMapper.getDiscussPostRows(0);
        System.out.println(count);

        // 测试 分页
        List<DiscussPost> list = discussPostMapper.getAllDiscussPost(0, 2, 10);
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @org.junit.Test
    public void TestMail() {

        // mailClient.sendMail("925030333@qq.com", "你好", "啊哈哈哈");
        // mailClient.sendMail("925030333@qq.com");



    }

    @org.junit.Test
    public void TestImage() {

        List<DiscussPost> allDiscussPost = discussPostMapper.getAllDiscussPost(0, 1, 1000);

        for (DiscussPost t: allDiscussPost) {
            t.setCreateTime(new Date());
            discussPostMapper.updateDiscussPost(new Date());
        }



    }

}
