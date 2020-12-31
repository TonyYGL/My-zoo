package com.example.petproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class StringRedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    private void initial() {
        valueOperations = stringRedisTemplate.opsForValue();
    }

    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public void setValue(String key, String value) {
        valueOperations.set(key, value);
    }

    public void setValueWithTTL(String key, String value, long timeout, TimeUnit timeUnit) {
        valueOperations.set(key, value, timeout, timeUnit);
    }

    public Optional<String> getValueByKey(String key) {
        String value = valueOperations.get(key);
        return Optional.ofNullable(value);
    }

    public boolean setTTL(String key, long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, timeout, timeUnit);
    }

    public long getTTL(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    public boolean deleteKey(String key) {
        return stringRedisTemplate.delete(key);
    }
}
