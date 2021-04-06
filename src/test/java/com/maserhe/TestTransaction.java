package com.maserhe;

import com.maserhe.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 * 测试事务
 *
 * @author Maserhe
 * @create 2021-04-05 17:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTransaction {

    @Autowired
    private AlphaService alphaService;

    @Test
    public void test() {
        alphaService.test();
    }
}
