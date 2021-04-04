package com.maserhe.controller;

import com.maserhe.entity.LoginTicket;
import com.maserhe.enums.TicketStatus;
import com.maserhe.mapper.LoginTicketMapper;
import com.maserhe.service.LoginTicketService;
import com.maserhe.util.CookieUtil;
import com.maserhe.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *  退出登陆
 * @author Maserhe
 * @create 2021-04-04 16:55
 */
@Controller
public class LogoutController {

    @Autowired
    private LoginTicketService loginTicketService;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        String ticket = CookieUtil.getValue(request, "ticket");
        // 更新登陆凭证为无效状态。
        loginTicketService.updateTicket(ticket, TicketStatus.INVALID);
        return "redirect:/site/login.html";

    }
}
