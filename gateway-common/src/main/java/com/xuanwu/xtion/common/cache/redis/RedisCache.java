package com.xuanwu.xtion.common.cache.redis;

import com.xuanwu.xtion.common.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.TimeUnit;

public class RedisCache extends RedisTemplate<String, Object> implements Cache {

    public RedisCache(JedisConnectionFactory connectionFactory) {
        super();
        super.setConnectionFactory(connectionFactory);
    }

    public RedisCache(JedisConnectionFactory connectionFactory, RedisSerializer redisSerializer) {
        this(connectionFactory);
        super.setDefaultSerializer(redisSerializer);
    }

    @Override
    public void set(String key, Object value, long timeout) {
        this.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return this.opsForValue().get(key);
    }

    @Override
    public void put(String key, String field, Object value) {
        this.opsForHash().put(key, field, value);
    }

    @Override
    public Object get(String key, String field) {
        return this.opsForHash().get(key, field);
    }

    @Override
    public void delete(String key, String... fields) {
        this.opsForHash().delete(key, fields);
    }
}
