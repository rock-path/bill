package com.psh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.psh.hik.common.Constant;
import com.psh.hik.common.ResultStatus;
import com.psh.hik.domain.BaseException;
import com.psh.hik.domain.BaseResultModel;
import com.psh.mapper.BillTaskTimeMapper;
import com.psh.service.BillTaskTimeService;
import com.psh.entity.BillTaskTime;
import com.psh.entity.request.ReqBillTaskTimeAdd;
import com.psh.entity.request.ReqBillTaskTimeUpdate;
import com.psh.entity.request.ReqBillTaskTimeQuery;
import com.psh.entity.response.ResBillTaskTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BillTaskTimeServiceImpl extends ServiceImpl<BillTaskTimeMapper, BillTaskTime> implements BillTaskTimeService {

    @Autowired
    private BillTaskTimeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel getOneById(Long id) {
        BillTaskTime entity = baseMapper.selectById(id);
        if (null == entity) {
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        ResBillTaskTime res = new ResBillTaskTime();
        BeanUtil.copyProperties(entity, res);
        return BaseResultModel.success(res);
    }

    @Resource
    private RedisTemplate redisTemplate;

    private final String TASK_TIME = "task_time";

    private final String ch1 = "task_time";


    @Override
    @Transactional
    public BaseResultModel insert(ReqBillTaskTimeAdd req) {
        //先删除之前的时间
        UpdateWrapper<BillTaskTime> queryWrapper = new UpdateWrapper();
        queryWrapper.eq("deleted", "0").set("deleted", "1");
        mapper.update(null, queryWrapper);
        BillTaskTime entity = new BillTaskTime();
        BeanUtil.copyProperties(req, entity);
        if (!this.save(entity)) {
            throw new BaseException(ResultStatus.INSERT_FAIL.getCode(), ResultStatus.INSERT_FAIL.getMessage());
        }
        //修改redis的过期时间
        redisTemplate.opsForValue().set(TASK_TIME, "过期时间", getTime(req.getCorn()), TimeUnit.SECONDS);
        return BaseResultModel.success();
    }

    private Long getTime(String corn) {
        Long res = null;
        //当前的小时和分钟
        Calendar calendar = Calendar.getInstance();
        int ho  = calendar.get(Calendar.HOUR_OF_DAY);
        int mi  = calendar.get(Calendar.MINUTE);
        if (corn.indexOf(Constant.CH7) >= 0) {
            String[] split = corn.split(Constant.CH7);
            Integer hours = Integer.valueOf(split[0]);
            Integer min = Integer.valueOf(split[1]);
            if (hours > ho) {
                //小时大于当前时，不管分钟如何，一定是今天的时间
                res = (hours - ho) * (3600l) + (min - mi) * 60;
            } else if (hours == ho) {
                if (min > mi) {
                    res = (hours - ho) * (3600l) + (min - mi) * 60;
                } else {
                    res = (24 * 3600l) + ((hours - ho) * (3600l) + (min - mi) * 60);
                }
            } else {
                res = (24 * 3600l) + ((hours - ho) * (3600l) + (min - mi) * 60);
            }
        }
        System.out.println(res);
        return res;
    }

    @Override
    @Transactional
    public BaseResultModel update(Long id, ReqBillTaskTimeUpdate req) {
        BillTaskTime exist = baseMapper.selectById(id);
        if (null == exist) {
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        BeanUtil.copyProperties(req, exist);

        if (!this.updateById(exist)) {
            throw new BaseException(ResultStatus.UPDATE_FAIL.getCode(), ResultStatus.UPDATE_FAIL.getMessage());
        }
        return BaseResultModel.success();

    }

    @Override
    @Transactional
    public BaseResultModel deleteOne(Long id) {
        if (!this.removeById(id)) {
            throw new BaseException(ResultStatus.DELETE_FAIL.getCode(), ResultStatus.DELETE_FAIL.getMessage());
        }
        return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel deleteBatch(List<Long> ids) {
        //批量删除
        if (!this.removeByIds(ids)) {
            throw new BaseException(ResultStatus.DELETE_BATCH_FAIL.getCode(), ResultStatus.DELETE_BATCH_FAIL.getMessage());
        }
        return BaseResultModel.success();


    }

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel<IPage<ResBillTaskTime>> page(Page<ResBillTaskTime> page, ReqBillTaskTimeQuery req) {
        List<ResBillTaskTime> res = mapper.listByPage(page, req);
        page.setRecords(res);
        return BaseResultModel.success(page);
    }
}
