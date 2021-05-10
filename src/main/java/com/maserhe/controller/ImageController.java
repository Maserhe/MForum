package com.maserhe.controller;

import com.google.code.kaptcha.Producer;
import com.maserhe.util.MD5Utils;
import com.maserhe.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 生成验证码的Controller
 *
 * @author Maserhe
 * @create 2021-04-03 14:46
 */
@Controller
public class ImageController {

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private Producer producer;

    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Autowired
    private RedisTemplate template;

    @RequestMapping("/kaptcha")
    public void getImage(HttpServletResponse response, HttpSession session) {
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);


        // session.setAttribute("kaptcha", text);
        String owner = MD5Utils.generateUUID();
        Cookie cookie = new Cookie("kaptcha", owner);
        // 设置有效时间 60 秒
        cookie.setMaxAge(60);
        cookie.setPath(contentPath);
        response.addCookie(cookie);

        // 将验证码存入redis
        String key = RedisUtil.getKaptchaKey(owner);
        BoundValueOperations operations = template.boundValueOps(key);
        operations.set(text, 60, TimeUnit.SECONDS);

        response.setContentType("image/png");
        ServletOutputStream os = null;
        try {
            os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("验证码生成错误" + e.getMessage());
        }
    }


}
