package com.psh.service;

import com.psh.entity.BillRecord;
import com.psh.entity.request.ReqBillRecordAdd;
import com.psh.entity.request.ReqBillRecordUpdate;
import com.psh.entity.request.ReqBillRecordQuery;
import com.psh.entity.response.ResBillRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.psh.hik.domain.BaseResultModel;
import java.util.List;

/**
* @Author makejava
* @Date 2022-01-28
* @Time 21:26:44
*消费记录表服务层
*/
public interface BillRecordService extends IService<BillRecord>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillRecordAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillRecordUpdate req);
    
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
    BaseResultModel<IPage<ResBillRecord>> page(Page<ResBillRecord> page, ReqBillRecordQuery req);
    
}