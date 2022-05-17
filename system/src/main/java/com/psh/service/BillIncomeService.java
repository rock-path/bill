package com.psh.service;

import com.psh.entity.BillIncome;
import com.psh.entity.request.ReqBillIncomeAdd;
import com.psh.entity.request.ReqBillIncomeUpdate;
import com.psh.entity.request.ReqBillIncomeQuery;
import com.psh.entity.response.ResBillIncome;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.psh.hik.domain.BaseResultModel;
import java.util.List;

/**
* @Author makejava
* @Date 2022-01-29
* @Time 21:39:20
*收入表服务层
*/
public interface BillIncomeService extends IService<BillIncome>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillIncomeAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillIncomeUpdate req);
    
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
    BaseResultModel<IPage<ResBillIncome>> page(Page<ResBillIncome> page, ReqBillIncomeQuery req);
    
}