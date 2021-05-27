package com.maserhe.service;

import com.maserhe.entity.LoginTicket;
import com.maserhe.entity.User;
import com.maserhe.enums.TicketStatus;
import com.maserhe.enums.UserStatus;
import com.maserhe.mapper.UserMapper;
import com.maserhe.util.MD5Utils;
import com.maserhe.util.MailClient;
import com.maserhe.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 14:44
 */
@Service
public class UserService implements UserStatus{

    @Autowired
    private UserMapper userMapper;

    // @Autowired
    // private LoginTicketMapper loginTicketMapper;

    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Value("${domain}")
    private String domain;
    @Value("${server.port}")
    private String port;

    @Autowired
    private MailClient client;

    @Autowired
    private RedisTemplate template;

    /**
     * 查询用户 通过id 使用 cache 重构
     * @param userId
     * @return
     */
    public User findUserById(int userId) {
        User user = getCache(userId);
        if (user == null) {
            user = initCache(userId);
        }
        return user;
    }

    /**
     * 查询用户 通过username
     * @param username
     * @return
     */
    public User findUserByName(String username) {return userMapper.selectByName(username);};

    /**
     * 查询用户 通过邮箱
     * @param email
     * @return
     */
    public User findUserByEmail(String email) {return userMapper.selectByEmail(email);};

    /**
     * 更新用户的Status 通过userid
     * @param userId
     * @param status
     * @return
     */
    public int updateUserStatus(int userId, int status) {
        // 清理缓存
        delCache(userId);
        return userMapper.updateStatus(userId, status);
    }


    /**
     * 注册用户
     * @param user
     * @return
     */
    public Map<String, Object> registerUser(User user) {

        // 查询用户信息是否合法
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能位空");
            return map;
        }

        // 查询用户是否存在
        User selectUser = userMapper.selectByName(user.getUsername());
        if (selectUser != null) {
            map.put("usernameMsg", "用户已经存在");
            return map;
        }
        selectUser = userMapper.selectByEmail(user.getEmail());
        if (selectUser != null) {
            map.put("emailMsg", "邮箱已经被注册了");
            return map;
        }

        // 可以创建用户了。
        // 随机生成 5位 字符串。作为salt
        String salt = MD5Utils.generateUUID().substring(0, 5);
        user.setSalt(salt);
        // 设置相应的密码, 通过明文密码 + salt 通过md5
        user.setPassword(MD5Utils.md5(user.getPassword() + salt));
        // status= 1代表已经激活

        user.setStatus(1);
        user.setType(0);
        user.setHeaderUrl("http://images.nowcoder.com/head/21t.png");
        user.setCreateTime(new Date());
        user.setActivationCode(MD5Utils.generateUUID().substring(0, 20));
        userMapper.insertUser(user);

        // 设置激活邮件
        // 配置激活链接 http://domain/contentPath/activeAccount/username/activation_code
        String url = "http://" + domain + ":"+ port + contentPath + "/activeAccount/" + user.getUsername() + "/" + user.getActivationCode();
        //map.put("url", url);

        // 发送邮件 String to, String username, String subject, String url
        // client.sendMailToActive(user.getEmail(), user.getUsername(), "激活邮件", url);
        return map;

    }
    /**
     * 用户名， 密码， 凭证有效时间
     * @param username
     * @param password
     * @param expiredSeconds
     * @return
     */
    public Map<String, Object> loginUser(String username, String password, long expiredSeconds) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isEmpty(username)) { map.put("usernameMsg", "账号不能为空"); return map;}
        if (StringUtils.isEmpty(password)) { map.put("passwordMsg", "密码不能为空"); return map;}
        // 验证账号
        User user = userMapper.selectByName(username);
        if (user == null) {
            map.put("usernameMsg", "账号不存在");
            return map;
        }
        if (user.getStatus() == NO_ACTIVATION) {
            map.put("usernameMsg", "账号未激活");
        }
        // 验证密码, 通过 密码加上一段 盐。
        password = MD5Utils.md5(password + user.getSalt());
        if (! user.getPassword().equals(password)) {
            map.put("passwordMsg", "密码错误");
            return map;
        }
        // 生成登陆凭证

        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(user.getId());
        ticket.setTicket(MD5Utils.generateUUID());

        ticket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds  * 1000));
        ticket.setStatus(TicketStatus.EFFECTIVE);
        // loginTicketMapper.insertLoginTicket(ticket);

        template.opsForValue().set(RedisUtil.getTicketKey(ticket.getTicket()), ticket);

        map.put("ticket", ticket.getTicket());
        return map;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

    /**
     * 优先从缓存中获取 用户
     * @param userId
     * @return
     */
    public User getCache(int userId) {
        String key = RedisUtil.getUserKey(userId);
        return (User) template.opsForValue().get(key);
    }

    /**
     * 取不到值缓存数据
     * @param userId
     * @return
     */
    public User initCache(int userId) {
        User user = userMapper.selectById(userId);
        String redisKey = RedisUtil.getUserKey(userId);
        template.opsForValue().set(redisKey, user, 3600, TimeUnit.SECONDS);
        return user;
    }

    /**
     * 数据变更时 删除缓存
     * @param userid
     */
    public void delCache(int userid) {
        String userKey = RedisUtil.getUserKey(userid);
        template.delete(userKey);
    }
}
