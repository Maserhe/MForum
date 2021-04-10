package com.maserhe;

import com.maserhe.service.RedisService;
import com.maserhe.util.RedisUtil;
import org.aspectj.lang.annotation.Around;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 * 测试Redis
 *
 * @author Maserhe
 * @create 2021-04-10 9:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    private RedisTemplate template;

    @Autowired
    private RedisService redisService;

    @Test
    public void test(){

        BoundSetOperations operations = template.boundSetOps(RedisUtil.getKey(109));
        operations.add("aaa");
        operations.add("aa");
        operations.add("aab");
        operations.add("aab");

    }

    @Test
    public void test1(){
        redisService.like(109,1);
        redisService.like(109,2);
        System.out.println(redisService.count(109));
    }
}
