package com.psh.hik.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * * redis分布式锁工具类
 */
public class RedisSync {

    private static final Logger logger = LoggerFactory.getLogger(RedisSync.class);

    /**
     * <一句话功能简述> 获取redis分布式锁
     * <功能详细描述> 建议业务处理后及时清理锁（freeRedisLock）;若不及时清理则需等到超时删除
     *
     * @param redisLockKey redis锁名称，不能重复
     * @return boolean true:获取锁成功，false：获取失败
     */
    public static boolean getRedisLock(RedisTemplate<String, String> redisTemplate, String redisLockKey) {
        try {
            int sleepMiniTime = 100; // 循环等待最小间隔时间
            for (int i = 0; i < 50; i++) { // 最多尝试50次获取锁
                Boolean unLockFlag = redisTemplate.opsForValue().setIfAbsent(redisLockKey, redisLockKey, 3, TimeUnit.MINUTES);
                if (unLockFlag != null && unLockFlag) { // 获取分布式锁成功
                    logger.debug("获取redis锁{}成功", redisLockKey);
                    return true;
                } else { // 获取分布式锁失败
                    Thread.sleep(sleepMiniTime);
                }
            }
        } catch (Exception e) {
            logger.error("获取redis锁异常!", e);
        }
        return false;
    }

    /**
     * <一句话功能简述> 获取redis分布式锁
     * <功能详细描述> 建议业务处理后及时清理锁（freeRedisLock）;若不及时清理则需等到超时删除
     *
     * @param redisLockKey redis锁名称
     * @param timeout      获取分布式锁的最大等待时间
     * @param redisTimeout redis锁超时时间，单位分钟
     * @return boolean true:获取到锁,false:未获取到锁
     */
    public static boolean getRedisLock(RedisTemplate<String, String> redisTemplate, String redisLockKey, int timeout, int redisTimeout) {
        try {
//            int sleepMiniTime = 100; // 循环等待最小间隔时间
//            if(timeout < 100){
//                timeout = sleepMiniTime;
//            }
//            for(int i=0; i < timeout/sleepMiniTime; i++) { // 自旋，在获取锁超时前，循环尝试获取
//            }Boolean unLockFlag = redisTemplate.opsForValue().setIfAbsent(redisLockKey, redisLockKey, redisTimeout, TimeUnit.MINUTES); // 上锁
//            if(unLockFlag != null && unLockFlag){ // 获取锁成功
//                logger.debug("获取redis锁{}成功", redisLockKey);
//                return true;
//            }else{ // 未获取到分布式锁，等待sleepMiniTime
//                Thread.sleep(sleepMiniTime);
//            }

            Boolean unLockFlag = redisTemplate.opsForValue().setIfAbsent(redisLockKey, redisLockKey, redisTimeout, TimeUnit.MINUTES); // 上锁
            if (unLockFlag != null && unLockFlag) { // 获取锁成功
                logger.debug("获取redis锁{}成功", redisLockKey);
                return true;
            }
        } catch (Exception e) {
            logger.error("获取redis分布式锁异常!", e);
        }
        return false;
    }

    /**
     * <一句话功能简述> 判断是否唯一运行
     * <功能详细描述> 利用redis分布式锁
     * author: zhanggw
     * 创建时间:  2021/12/1
     *
     * @param redisLockKey redis key
     * @param timeout      redis key超时时间，单位毫秒
     * @return boolean true:唯一运行 false:非唯一运行
     */
    public static boolean isUniqueRun(RedisTemplate<String, String> redisTemplate, String redisLockKey, int timeout) {
        try {
            Boolean unLockFlag = redisTemplate.opsForValue().setIfAbsent(redisLockKey, redisLockKey, timeout, TimeUnit.MILLISECONDS); // 上锁
            if (unLockFlag != null && unLockFlag) { // 唯一运行
                return true;
            }
        } catch (Exception e) {
            logger.error("判断唯一运行异常!", e);
        }
        return false;
    }

    /**
     * <一句话功能简述> 判断是否唯一运行
     * <功能详细描述> 利用redis分布式锁
     * author: zhanggw
     * 创建时间:  2021/12/1
     *
     * @param redisLockKey redis key
     * @param timeout      redis key超时时间
     * @param timeUnit     超时时间单位
     * @return boolean true:唯一运行 false:非唯一运行
     */
    public static boolean isUniqueRun(RedisTemplate<String, String> redisTemplate, String redisLockKey, int timeout, TimeUnit timeUnit) {
        try {
            Boolean unLockFlag = redisTemplate.opsForValue().setIfAbsent(redisLockKey, redisLockKey, timeout, timeUnit); // 上锁
            if (unLockFlag != null && unLockFlag) { // 唯一运行
                return true;
            }
        } catch (Exception e) {
            logger.error("判断唯一运行异常!", e);
        }
        return false;
    }

    /**
     * <一句话功能简述> 清理redis锁
     * <功能详细描述>
     * author: zhanggw
     * 创建时间:  2021/1/19
     *
     * @param redisTemplate redis连接
     * @param redisLockKey  redis key锁名称
     */
    public static void freeRedisLock(RedisTemplate<String, String> redisTemplate, String redisLockKey) {
        redisTemplate.delete(redisLockKey);
        logger.debug("已清理redis锁{}", redisLockKey);
    }


}
