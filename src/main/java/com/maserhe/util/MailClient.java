package com.maserhe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-03 14:04
 */
@Component
public class MailClient {

    private final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendMail(String to, String subject, String text) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            // 这里设置 true 表明是一个 html
            helper.setText(text, true);
            sender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败" + e.getMessage());
        }
    }

    /**
     * 用户注册激活链接
     * @param to    邮件接收者
     * @param username  用户名
     * @param subject   邮件主题
     * @param url   激活链接
     */
    public void sendMailToActive(String to, String username, String subject, String url) {

        // 使用模板引擎处理 信息
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("email", to);
        context.setVariable("url", url);
        String text = templateEngine.process("/mail/activation.html", context);
        sendMail(to, subject, text);

    }


}
