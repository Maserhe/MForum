package com.maserhe;

import com.maserhe.entity.DiscussPost;
import com.maserhe.entity.User;
import com.maserhe.mapper.DiscussPostMapper;
import com.maserhe.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)

public class TestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
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

    @Test
    public void contextLoad() {
        int count = discussPostMapper.getDiscussPostRows(0);
        System.out.println(count);

        // 测试 分页
        List<DiscussPost> list = discussPostMapper.getAllDiscussPost(0, 0, 10);
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
