package com.maserhe.controller.interceptor;

import com.maserhe.entity.LoginTicket;
import com.maserhe.entity.User;
import com.maserhe.enums.TicketStatus;
import com.maserhe.enums.UserStatus;
import com.maserhe.service.LoginTicketService;
import com.maserhe.service.UserService;
import com.maserhe.util.CookieUtil;
import com.maserhe.util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-04 14:09
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private LoginTicketService loginTicketService;

    @Autowired
    private UserService userService;


    @Autowired
    private HostHolder hostHolder;

    @Value("${server.servlet.context-path}")
    private String contentPath;

    /**
     * 先判断 cookie 中有没有凭证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtil.getValue(request, "ticket");

        if (ticket != null) {
            // 检查凭证是否有效
            logger.info(ticket + "用户凭证");
            LoginTicket loginTicket = loginTicketService.findTicket(ticket);
            if (loginTicket.getStatus() == TicketStatus.EFFECTIVE && loginTicket.getExpired().after(new Date())) {
                User user = userService.findUserById(loginTicket.getUserId());
                hostHolder.setUser(user);
                logger.info(user + "登陆成功");
            } else {
                response.sendRedirect(contentPath + "/site/login.html");
                return false;
            }
        } else {
            response.sendRedirect(contentPath + "/site/login.html");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clearUser();
    }
}
