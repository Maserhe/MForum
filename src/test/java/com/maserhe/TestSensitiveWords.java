package com.maserhe;

import com.maserhe.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 描述:
 *  测试敏感词读取
 * @author Maserhe
 * @create 2021-04-04 20:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSensitiveWords {
    
    @Autowired
    private SensitiveFilter sensitiveFilter;
    
    @Test
    public void Test() {

        InputStream os = this.getClass().getClassLoader().getResourceAsStream("sensitiveWords.properties");
        BufferedReader reader = new BufferedReader(new InputStreamReader(os));
        try {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void TestString() {
        String test = "你是傻子，啊哈哈哈";
        String filter = sensitiveFilter.filter(test);
        System.out.println(filter);
    }
}
