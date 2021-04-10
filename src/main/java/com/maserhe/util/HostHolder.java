package com.maserhe.util;

import com.maserhe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 持有用户的信息， 用于代替Session 对象。
 * @author Maserhe
 * @create 2021-04-04 14:31
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new InheritableThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    /**
     * 清理当前用户
     */
    public void clearUser() {
        users.remove();
    }
}
