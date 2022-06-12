package com.psh.scheduledTask;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.psh.entity.BillTaskTime;
import com.psh.mapper.BillTaskTimeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

//@Configuration
//@EnableScheduling
public class CompleteScheduleConfig implements SchedulingConfigurer {


    @Resource
    private BillTaskTimeMapper billTaskTimeMapper;

    /**
     * 执行定时任务.
     * 这个定时任务，在执行完上一次任务后，会设置新的定时任务时间，并不灵活，只是简便
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行定时任务2: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    List<BillTaskTime> li = billTaskTimeMapper.selectList(new QueryWrapper<BillTaskTime>());
                    String cron = null;
                    for (BillTaskTime bi : li) {
                        if (bi.getTaskId().equals("1") && bi.getDeleted().equals("0")) {
                            cron = bi.getCorn();
                        }
                    }
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

}