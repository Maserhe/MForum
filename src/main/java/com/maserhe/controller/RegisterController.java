package com.maserhe.controller;

import com.maserhe.entity.User;
import com.maserhe.enums.UserStatus;
import com.maserhe.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 * 注册控制
 *
 * @author Maserhe
 * @create 2021-04-03 18:38
 */
@Controller
public class RegisterController implements UserStatus {

    @Autowired
    private UserService userService;

    /**
     * 注册请求
     * @param model
     * @param user
     * @return
     */
    @RequestMapping (path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user) {
        // 进行注册
        Map<String, Object> map = userService.registerUser(user);
        model.addAllAttributes(map);

        if (map.containsKey("url")) {
            // 注册成功。
            model.addAttribute("registerMsg", "注册成功,请尽快激活");
        }

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey() + " ------ "  + next.getValue().toString());
        }
        return "/site/register";
    }

    /**
     *  为用户激活账号
     * @param username
     * @param activeCode
     * @return
     */
    @GetMapping("/activeAccount/{username}/{activeCode}")
    public String activeAccount(@PathVariable String username, @PathVariable String activeCode) {

        // 检查用户名
        if (StringUtils.isBlank(username) || StringUtils.isBlank(activeCode)) return "/site/register";
        // 查询用户
        User user = userService.findUserByName(username);
        if (user == null) return "/site/register";
        // 验证激活码
        if (!user.getActivationCode().equals(activeCode)) return "/site/register";

        // 开始激活用户
        userService.updateUserStatus(user.getId(), ACTIVATION);
        return "/site/operate-result";
    }
}
