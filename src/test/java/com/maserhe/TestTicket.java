package com.maserhe;

import com.maserhe.entity.LoginTicket;
import com.maserhe.enums.TicketStatus;
import com.maserhe.mapper.LoginTicketMapper;
import com.maserhe.util.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


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

        // loginTicketMapper.updateStatus("adsfadfd", 0);
        // LoginTicket ticket1 = loginTicketMapper.selectTicket("adsfadfd");
        // System.out.println(ticket1);

        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(123456);
        ticket.setTicket(MD5Utils.generateUUID());
        // Date date = new Date(System.currentTimeMillis() + 2592000 * 10000);
        long a = System.currentTimeMillis();
        int b = 24 * 30 * 3600 * 1000;

        System.out.println(a + " ------" + b);
        System.out.println(new Date(a));
        System.out.println(new Date(a + b));


        Date date = new Date(System.currentTimeMillis() + 24*30*3600 * 1000);
        ticket.setExpired(date);


        ticket.setStatus(TicketStatus.EFFECTIVE);
        loginTicketMapper.insertLoginTicket(ticket);

    }

    @Test
    public void testUpdateTicket() {
        // loginTicketMapper.updateDateAndStatus("8d0a8210242546f78b4365cb64fdb4a2", TicketStatus.EFFECTIVE, new Date(System.currentTimeMillis() + 24 * 3600 * 1000 ));
        List<LoginTicket> list = loginTicketMapper.selectAllTicket();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
