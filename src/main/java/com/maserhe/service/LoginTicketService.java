package com.maserhe.service;

import com.maserhe.entity.LoginTicket;
import com.maserhe.enums.TicketStatus;
import com.maserhe.mapper.LoginTicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述:
 * 登陆凭证的 业务处理
 *
 * @author Maserhe
 * @create 2021-04-04 14:18
 */
@Service
public class LoginTicketService {


    @Autowired
    private LoginTicketMapper loginTicketMapper;

    /**
     *  查询用户凭证
     * @param ticket
     * @return
     */
    public LoginTicket findTicket(String ticket) {
        return loginTicketMapper.selectTicket(ticket);
    }

    /**
     *  更新 用户的状态
     * @param ticket
     * @param status
     * @return
     */
    public int updateTicket(String ticket, int status) {
        return loginTicketMapper.updateStatus(ticket, status);
    }

    /**
     * 添加剂用户凭证
     * @param ticket
     * @return
     */
    public int addTicket(LoginTicket ticket) {
        return loginTicketMapper.insertLoginTicket(ticket);
    }

    /**
     * 更新用户的 状态 和 失效时间。
     * @param ticket
     * @param status
     * @param expired
     * @return
     */
    public int updateStatusAndExpired(String ticket, int status, Date expired) {
        return loginTicketMapper.updateDateAndStatus(ticket, status, expired);
    }
}
