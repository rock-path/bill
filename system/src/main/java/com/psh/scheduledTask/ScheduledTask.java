package com.psh.scheduledTask;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.psh.entity.BillLogResource;
import com.psh.entity.BillRequestUrl;
import com.psh.hik.common.Constant;
import com.psh.hik.util.RedisSync;
import com.psh.log.LogConstant;
import com.psh.mapper.BillLogResourceMapper;
import com.psh.mapper.BillRequestUrlMapper;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.psh.log.LogConstant.SYNC1;
import static com.psh.log.LogConstant.SYNC2;

@Component
@EnableScheduling   // 2.开启定时任务
public class ScheduledTask {

    private Logger logger = Logger.getLogger("ScheduledTask");

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BillRequestUrlMapper billRequestUrlMapper;

    @Resource
    private BillLogResourceMapper billLogResourceMapper;

    @Scheduled(cron = "0 */1 * * * ?")
    //或直接指定时间间隔
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        if (RedisSync.getRedisLock(redisTemplate, SYNC1, 1, 180 * 1000)) {
            logger.info("定时任务：获取url访问记录--------》》开始");
            List<String> list = new ArrayList<>();
            list.add(Constant.GATEWAYURL);
            list.add(Constant.SYSTEMURL);
            for (String ss : list) {
                savr(ss);
            }
            logger.info("定时任务：获取url访问记录--------》》结束");
        }
    }

    private void savr(String ss) {
        List list = redisTemplate.opsForHash().values(ss);
        if (list.size() > 100) {
            for (int a = 0; a < list.size(); a++) {
                // 将Json字符串反序列化为Java对象
                BillRequestUrl billRequestUrl = JSONUtil.toBean(list.get(a).toString(), BillRequestUrl.class);
                redisTemplate.opsForHash().delete(ss, billRequestUrl.getUuid());
                logger.info("删除redis数据：" + billRequestUrl);
                billRequestUrlMapper.insert(billRequestUrl);
            }
        }
    }


    @Scheduled(cron = "0 */30 * * * ?")
    //或直接指定时间间隔
    //@Scheduled(fixedRate=5000)
    private void logResource() {
        if (RedisSync.getRedisLock(redisTemplate, SYNC2, 1, 180 * 1000)) {
            long startTime = System.currentTimeMillis();
            logger.info("定时任务：同步需要记录操作日志的静态资源--------》》开始");
            QueryWrapper<BillLogResource> queryWrapper = new QueryWrapper();
            List<BillLogResource> list = billLogResourceMapper.selectList(queryWrapper);
            redisTemplate.delete(LogConstant.LOGS);
            for (BillLogResource billLogResource : list) {
                if (billLogResource.getDeleted().equals("1")) {
                    redisTemplate.opsForHash().delete(LogConstant.LOGS, billLogResource.getId());
                } else {
                    redisTemplate.opsForHash().put(LogConstant.LOGS, billLogResource.getId(), JSONUtil.toJsonStr(billLogResource));
                }
            }
            long endTime = System.currentTimeMillis() - startTime;
            logger.info("定时任务：同步需要记录操作日志的静态资源--------》》结束,耗时" + endTime + "ms");
        }
    }


}
