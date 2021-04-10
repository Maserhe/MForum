package com.maserhe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 描述:
 * redis的工具类
 *
 * @author Maserhe
 * @create 2021-04-10 9:47
 */

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> template(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // 设置key的序列话格式
        template.setKeySerializer(RedisSerializer.string());
        // 设置value的序列话方式
        template.setValueSerializer(RedisSerializer.json());
        // 设置hashValue的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());
        // 设置hash key的蓄力阿虎方式
        template.setHashKeySerializer(RedisSerializer.string());
        template.afterPropertiesSet();
        return template;
    }

}
