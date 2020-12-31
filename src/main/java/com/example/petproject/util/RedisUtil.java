package com.example.petproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    private ValueOperations<String, Object> valueOperations;
    private ListOperations<String, Object> listOperations;
    private HashOperations<String, String, Object> hashOperations;
    private SetOperations<String, Object> setOperations;
    private ZSetOperations<String, Object> zsetOperations;

    @PostConstruct
    private void initial() {
        valueOperations = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
        hashOperations = redisTemplate.opsForHash();
        setOperations = redisTemplate.opsForSet();
        zsetOperations = redisTemplate.opsForZSet();
    }


    public long incre(String key, long value) {
        return valueOperations.increment(key, value);
    }

    public long decre(String key, long value) {
        return valueOperations.decrement(key, value);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public long leftPush(String key, Object... values) {
        return listOperations.leftPush(key, values);
    }

    public Optional<Object> leftPop(String key) {
        return Optional.ofNullable(listOperations.leftPop(key));
    }

    public long rightPush(String key, Object... values) {
        return listOperations.rightPush(key, values);
    }

    public Optional<Object> rightPop(String key) {
        return Optional.ofNullable(listOperations.rightPop(key));
    }

    public long getListSize(String key) {
        return listOperations.size(key);
    }

    public List<Object> getList(String key, long start, long end) {
        return listOperations.range(key, start, end);
    }

    public boolean setTTL(Object key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public void putHash(String key, String hashKey, Object HashValue) {
        hashOperations.put(key, hashKey, HashValue);
    }

    public long deleteHashKey(String key, String hashKey) {
        return hashOperations.delete(key, hashKey);
    }

    public Object getHash(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }

    public long addSet(String key, Object... values) {
        return setOperations.add(key, values);
    }

    public Set<Object> getSet(String key) {
        return setOperations.members(key);
    }

    public long removeSetElement(String key, Object... values) {
        return setOperations.remove(key, values);
    }

    public long getSetSize(String key) {
        return setOperations.size(key);
    }

    public boolean addZSet(String key, Object object, double score) {
        return zsetOperations.add(key, object, score);
    }

    public Set rangeZSet(String key, long start, long end) {
        return zsetOperations.range(key, start, end);
    }

    public Set<Object> rangeZSetByScore(String key, double min, double max) {
        return zsetOperations.rangeByScore(key, min, max);
    }

    public long removeZSetElement(String key, Object... values) {
        return zsetOperations.remove(key, values);
    }
}
