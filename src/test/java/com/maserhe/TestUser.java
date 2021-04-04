package com.maserhe;

import com.maserhe.entity.User;
import com.maserhe.mapper.UserMapper;
import com.maserhe.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-03 20:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUser {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void Test() {
        User user = new User();
        user.setUsername("sdfadfasdffad");
        user.setCreateTime(new Date());
        user.setStatus(0);
        user.setEmail("dfadfa@qq.com");
        int i = userMapper.insertUser(user);
        System.out.println(i);
    }

    /**
     * 测试 注册 用户
     */
    @Test
    public void TestUserService() {
        User user = new User();
        user.setUsername("MaserheNB");
        user.setPassword("123456");
        user.setEmail("925030333@qq.com");

        Map<String, Object> map = userService.registerUser(user);
        Set<Map.Entry<String, Object>> set = map.entrySet();

        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {

            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey() + " ------ " + next.getValue().toString());

        }


    }
}
