package com.maserhe;

import com.maserhe.entity.LoginTicket;
import com.maserhe.mapper.LoginTicketMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-03 15:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTicket {

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void test() {
        // LoginTicket ticket = new LoginTicket();
        // ticket.setTicket("adsfadfd");
        // ticket.setStatus(0);
        // int i = loginTicketMapper.insertLoginTicket(ticket);
        // System.out.println(i);

        loginTicketMapper.updateStatus("adsfadfd", 0);
        LoginTicket ticket1 = loginTicketMapper.selectTicket("adsfadfd");
        System.out.println(ticket1);

    }
}
