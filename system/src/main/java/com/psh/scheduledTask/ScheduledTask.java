package com.psh.scheduledTask;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.psh.entity.BillRequestUrl;
import com.psh.hik.common.Constant;
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

@Component
@EnableScheduling   // 2.开启定时任务
public class ScheduledTask {

    private Logger logger = Logger.getLogger("ScheduledTask");

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BillRequestUrlMapper billRequestUrlMapper;


    @Scheduled(cron = "0 */1 * * * ?")
    //或直接指定时间间隔
    //@Scheduled(fixedRate=5000)
    public void configureTasks() {
        logger.info("获取url访问记录");
        List<String> list = new ArrayList<>();
        list.add(Constant.GATEWAYURL);
        list.add(Constant.SYSTEMURL);
        for (String ss : list) {
            savr(ss);
        }
        logger.info("扫描url访问记录成功");
    }

    private void savr(String ss) {
        List list = redisTemplate.opsForHash().values(ss);
        if (list.size() > 100) {
            redisTemplate.delete(ss);
            for (int a = 0; a < list.size(); a++) {
                // 将Json字符串反序列化为Java对象
                BillRequestUrl billRequestUrl = JSONUtil.toBean(list.get(a).toString(), BillRequestUrl.class);
                billRequestUrlMapper.insert(billRequestUrl);
            }
        }
    }


}
