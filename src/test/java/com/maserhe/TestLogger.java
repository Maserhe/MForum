package com.maserhe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 22:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLogger {

    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);

    @Test
    public void test() {
        logger.debug("哈哈哈哈");
        logger.error("错误");
        logger.info("你好");
        logger.warn("警告");
    }
}
