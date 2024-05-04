package com.etc.order.service.manager.impl;

import com.etc.order.service.manager.InventoryManager;
import com.etc.order.service.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 库存服务类
 * @author Yuxiang Liu
 * */
@Service
public class InventoryManagerImpl implements InventoryManager {


    private static final String REDIS_KEY_PREFIX = "inventory:";
    private static final String REDIS_LOCK_PREFIX = "lock:";
    private static final long LOCK_EXPIRE_TIME = 3000; // 锁过期时间，单位毫秒
    private static final int RETRY_TIMES = 3; // 重试次数


    @Autowired
    private RedisUtil redisUtil;


    /**
     * @param productId 商品id
     * @param quantity  商品数量
     * @return boolean
     * */
    @Override
    public boolean deductInventory(String productId,Integer quantity) {
        String lockKey = REDIS_LOCK_PREFIX + REDIS_KEY_PREFIX+productId;

        for (int i = 0; i < RETRY_TIMES; i++) {
            boolean lockAcquired = acquireLock(lockKey);
            if (!lockAcquired) {
                continue;// 获取锁失败，稍后重试
            }
            try {
                if (!checkInventory(productId,quantity)) {// 检查库存是否充足
                    return false; // 库存不足，扣减失败
                }
                return updateInventory(productId,quantity);// 扣减库存
            } finally {
                releaseLock(lockKey);// 释放锁
            }
        }
        return false; // 重试多次后仍然失败
    }

    /**
     * 获取分布式锁
     * */
    private boolean acquireLock(String lockKey) {
        String lockValue = String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE_TIME + 1);
        return redisUtil.set(lockKey, lockValue, LOCK_EXPIRE_TIME);
    }

    /**
     * 释放分布式锁
     * */
    private void releaseLock(String lockKey) {
        redisUtil.del(lockKey);
    }

    /**
     * 这里只模拟扣减缓存中的库存，实际情况可能需要查询数据库并更新库存
     * 检查库存是否充足
     * @param productId 商品库存键 ; quantity 商品库存数量
     * @return true： 库存充足； false：库存不足
     *
     * */
    private boolean checkInventory(String productId,Integer quantity) {
        int num= (int) redisUtil.get(productId);
        if(quantity<=num){
            return true;
        }
        return false;
    }

    /**
     * 更新库存逻辑，这里只模拟更新缓存中的库存
     * */
    private boolean updateInventory(String  productId,Integer quantity) {
        int num= (int) redisUtil.get(productId);
        return redisUtil.set(productId,num-quantity);
    }

    /**
     * 库存回滚
     * */
    private boolean rollbackInventory(String  productId,Integer quantity) {
        int num= (int) redisUtil.get(productId);
        return redisUtil.set(productId,num+quantity);
    }
}
