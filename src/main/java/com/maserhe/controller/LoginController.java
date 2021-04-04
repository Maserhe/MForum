package com.maserhe.controller;

import com.maserhe.enums.LoginParam;
import com.maserhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 描述:
 *  登陆控制
 * @author Maserhe
 * @create 2021-04-03 15:31
 */
@Controller
public class LoginController implements LoginParam {

    @Autowired
    private UserService userService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @PostMapping("/login")
    public String login(Model model, String username, String password, String code, boolean rememberMe, HttpSession session, HttpServletResponse response) {
        // 首先判断验证码，
        String text = (String) session.getAttribute("kaptcha");
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(code) || !text.equalsIgnoreCase(code)) {
            model.addAttribute("codeMsg", "验证码不正确");
            return "/site/login";
        }

        long expiredSeconds = rememberMe ? REMEMBER_EXPIRED_SECONDS: DEFAULT_EXPIRED_SECONDS;

        Map<String, Object> map = userService.loginUser(username, password, expiredSeconds);
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge((int) expiredSeconds);
            response.addCookie(cookie);
            return "redirect:/index.html";
        }

        model.addAllAttributes(map);
        return "/site/login.html";
    }

}
