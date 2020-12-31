package com.example.petproject;

import com.example.petproject.bean.LoginForm;
import com.example.petproject.util.RedisUtil;
import com.example.petproject.util.StringRedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisTester {

    @Autowired
    private StringRedisUtil stringRedisUtil;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testHasKey() {
        System.out.println(redisUtil.hasKey("list"));
    }

    @Test
    public void testInsert() {
        stringRedisUtil.setValue("name", "keng");
        stringRedisUtil.setTTL("name", 60, TimeUnit.SECONDS);
    }

    @Test
    public void testInsertWithTTL() {
        stringRedisUtil.setValueWithTTL("myname", "aaa", 20, TimeUnit.SECONDS);
    }

    @Test
    public void testGet() {
        Optional<String> stringOptional = stringRedisUtil.getValueByKey("name");
        assertEquals("keng", stringOptional.orElse("no user"));
    }

    @Test
    public void testDelete() {
        boolean b = stringRedisUtil.deleteKey("name");
        System.out.println("b = " + b);
    }

    @Test
    public void testLPush() {
        redisUtil.leftPush("loginList",new LoginForm());
        redisUtil.leftPush("loginList",new LoginForm());
    }

    @Test
    public void testPop() {
        System.out.println(redisUtil.rightPop("loginList"));
        System.out.println(redisUtil.leftPop("loginList"));
    }

    @Test
    public void testTTL() {
        boolean b = redisUtil.setTTL("name", 20, TimeUnit.SECONDS);
        System.out.println("b = " + b);
    }

    @Test
    public void testGetSize() {
        long l = redisUtil.getListSize("loginList");
        assertEquals(4, l);
    }

    @Test
    public void testPut() {
        redisUtil.putHash("UserInfo", "name", "keng");
        redisUtil.putHash("UserInfo", "account", "pox");
    }

    @Test
    public void testGetHash() {
        assertEquals("keng", redisUtil.getHash("UserInfo", "name"));
    }

    @Test
    public void testDeleteHashKey() {
        redisUtil.deleteHashKey("UserInfo", "name");
    }

    @Test
    public void testAddSet() {
        System.out.println(redisUtil.addSet("set", "aaa", "aaa", "ccc"));
    }

    @Test
    public void testGetSet() {
        Set<Object> set = redisUtil.getSet("set");
        Set<Object> expect = new HashSet<>(Arrays.asList("aaa", "ccc"));
        assertEquals(expect, set);
    }

    @Test
    public void testRemoveSet() {
        System.out.println(redisUtil.removeSetElement("set", "aaa", "ddd"));
    }

    @Test
    public void testGetSetSize() {
        assertEquals(1, redisUtil.getSetSize("set"));
    }

    @Test
    public void testAddZSet() {
        redisUtil.addZSet("ZSet", "1!!!", 0);
    }

    @Test
    public void testRange() {
        Set<Object> expect = new HashSet<>(Collections.singletonList("1!!!"));
        assertEquals(expect, redisUtil.rangeZSet("ZSet", 0, 2));
    }

    @Test
    public void testRangeByScore() {
        Set<Object> expect = new HashSet<>(Collections.singletonList("1!!!"));
        assertEquals(expect, redisUtil.rangeZSet("ZSet", 0, 2));
    }

    @Test
    public void testRemoveZSet() {
        System.out.println(redisUtil.removeZSetElement("ZSet", "1!!!", "second"));
    }

    @Test
    public void testIncre() {
        redisUtil.incre("name", 10);
    }

    @Test
    public void testDecre() {
        redisUtil.decre("name", 1);
    }
}
