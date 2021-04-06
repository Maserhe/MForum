package com.maserhe.service;

import com.maserhe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *  测试事务
 * @author Maserhe
 * @create 2021-04-05 15:24
 */
@Service
public class AlphaService {

    @Autowired
    private UserService userService;

    @Autowired
    private DiscussPostService discussPostService;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void test() {
        User user = new User();
        user.setUsername("中中中");
        user.setPassword("llalalaalal1");
        user.setEmail("lilililiili1");
        userService.addUser(user);
        Integer.valueOf("abc");
    }

}
