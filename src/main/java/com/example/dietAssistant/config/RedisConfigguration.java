package com.example.dietAssistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfigguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置redis连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置redis系列化key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //开启事务支持
        redisTemplate.setEnableTransactionSupport(true);

        return redisTemplate;
    }
}
