package com.etc.order.service.utils;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * @author Yuxiang Liu
 * */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入缓存
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(final String key) {
        Boolean isExists = redisTemplate.hasKey(key);
        return BooleanUtils.isTrue(isExists);
    }

    /**
     * 读取缓存
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }



    /**
     * 哈希 添加
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 列表添加
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表获取
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * 集合添加
     */
    public void addSet(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 删除集合下的所有值
     */
    public void removeSetAll(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        Set<Object> objectSet = set.members(key);
        if (objectSet != null && !objectSet.isEmpty()) {
            for (Object o : objectSet) {
                set.remove(key, o);
            }
        }
    }

    /**
     * 判断set集合里面是否包含某个元素
     */
    public Boolean isMember(String key, Object member) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.isMember(key, member);
    }

    /**
     * 集合获取
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     */
    public void zAdd(String key, Object value, double source) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, source);
    }

    /**
     * 有序集合获取指定范围的数据
     */
    public Set<Object> rangeByScore(String key, double source, double source1) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.rangeByScore(key, source, source1);
    }

    /**
     * 有序集合升序获取
     */
    public Set<Object> range(String key, Long source, Long source1) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.range(key, source, source1);
    }

    /**
     * 有序集合降序获取
     */
    public Set<Object> reverseRange(String key, Long source, Long source1) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.reverseRange(key, source, source1);
    }

    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @return
     */

    public Boolean expire(String key,long time){
        try {
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断key是否存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     */
    public void del(String... key){
        if (key!=null&&key.length>0){
            if (key!=null && key.length==1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 设置键并设置有效时间
     */
    public boolean set(String key,Object value,long time){
        try {
            if (time>0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
                return true;
            }else {
                redisTemplate.opsForValue().set(key,value);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}