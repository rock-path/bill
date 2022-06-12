package com.psh.service;

import com.psh.entity.BillTaskTime;
import com.psh.entity.request.ReqBillTaskTimeAdd;
import com.psh.entity.request.ReqBillTaskTimeUpdate;
import com.psh.entity.request.ReqBillTaskTimeQuery;
import com.psh.entity.response.ResBillTaskTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.psh.hik.domain.BaseResultModel;

/**
* @Author psh
* @Date 2022-06-12
* @Time 13:48:54
*动态定时任务执行时间服务层
*/
public interface BillTaskTimeService extends IService<BillTaskTime>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillTaskTimeAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillTaskTimeUpdate req);
    
    /**
    * 单个删除
    */
    BaseResultModel deleteOne(Long id);
    
    /**
    * 批量删除
    */
    BaseResultModel deleteBatch(List<Long> ids);
    
    /**
    *分页查询
    */
    BaseResultModel<IPage<ResBillTaskTime>> page(Page<ResBillTaskTime> page, ReqBillTaskTimeQuery req);
    
}
