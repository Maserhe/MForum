package com.maserhe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-04 16:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTime {

    @Test
    public void test() {
        Date date = new Date(System.currentTimeMillis() + 2592000 * 10000);

        System.out.println(date);

    }
}
