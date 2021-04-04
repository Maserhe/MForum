package com.maserhe;

import com.maserhe.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述: 测试发送邮件
 *
 * @author Maserhe
 * @create 2021-04-03 21:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMail {

    @Autowired
    private MailClient client;

    @Test
    public void Test() {

        client.sendMailToActive("925030333@qq.com", "Maserhe", "激活账号", "https://maserhe.top");
    }

}
