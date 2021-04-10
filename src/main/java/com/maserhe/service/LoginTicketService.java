package com.maserhe.service;

import com.maserhe.entity.LoginTicket;
import com.maserhe.mapper.LoginTicketMapper;
import com.maserhe.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 登陆凭证的 业务处理 重构 登陆使用Redis
 *
 * @author Maserhe
 * @create 2021-04-04 14:18
 */
@Service
public class LoginTicketService {


    // @Autowired
    // private LoginTicketMapper loginTicketMapper;

    @Autowired
    private RedisTemplate template;

    /**
     *  查询用户凭证
     * @param ticket
     * @return
     */
    public LoginTicket findTicket(String ticket) {
        // 获取Redis 的 ticket
        String key = RedisUtil.getTicketKey(ticket);
        return (LoginTicket) template.opsForValue().get(key);
    }

    /**
     *  更新 用户的状态
     * @param ticket
     * @param status
     * @return
     */
    public void updateTicket(String ticket, int status) {
        String key = RedisUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) template.opsForValue().get(key);
        loginTicket.setStatus(1);
        template.opsForValue().set(key, loginTicket);
        // return loginTicketMapper.updateStatus(ticket, status);
    }

    /**
     * 添加剂用户凭证
     * @param ticket
     * @return
     */
    public void addTicket(LoginTicket ticket) {
        // 设置key
        String key = RedisUtil.getTicketKey(ticket.getTicket());
        template.opsForValue().set(key, ticket);
        // return loginTicketMapper.insertLoginTicket(ticket);
    }

    /**
     * 更新用户的 状态 和 失效时间。
     * @param ticket
     * @param status
     * @param expired
     * @return
     */
    public void updateStatusAndExpired(String ticket, int status, Date expired) {
        String key = RedisUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) template.opsForValue().get(key);
        loginTicket.setStatus(status);
        loginTicket.setExpired(expired);
        template.opsForValue().set(key, loginTicket);

    }

    /*
    public List<LoginTicket> findAllTicket(){
        return loginTicketMapper.selectAllTicket();
    }
     */
}
