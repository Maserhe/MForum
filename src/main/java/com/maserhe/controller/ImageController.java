package com.maserhe.controller;

import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @RequestMapping("/kaptcha")
    public void getImage(HttpServletResponse response, HttpSession session) {
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        session.setAttribute("kaptcha", text);
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
