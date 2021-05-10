package com.maserhe.controller;

import com.maserhe.enums.LoginParam;
import com.maserhe.service.UserService;
import com.maserhe.util.HostHolder;
import com.maserhe.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private RedisTemplate template;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping("/login")
    public String login(Model model, String username, String password, String code, boolean rememberMe, HttpServletResponse response, @CookieValue("kaptcha") String owner) {
        // 首先获取 cookie中随机的
        if (StringUtils.isEmpty(owner)) {
            return "/site/login";
        }
        // 获取Redis 获取key,然后获取验证码。
        String key = RedisUtil.getKaptchaKey(owner);
        String text = (String) template.opsForValue().get(key);


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
            model.addAllAttributes(map);
            return "redirect:/index.html";
        }

        model.addAllAttributes(map);
        return "redirect:/site/login.html";
    }

}
