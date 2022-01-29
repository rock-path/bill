package com.psh.service;

import com.psh.entity.BillType;
import com.psh.entity.request.ReqBillTypeAdd;
import com.psh.entity.request.ReqBillTypeUpdate;
import com.psh.entity.request.ReqBillTypeQuery;
import com.psh.entity.response.ResBillType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.psh.hik.domain.BaseResultModel;
import java.util.List;

/**
* @Author makejava
* @Date 2022-01-28
* @Time 21:26:56
*消费类型表服务层
*/
public interface BillTypeService extends IService<BillType>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillTypeAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillTypeUpdate req);
    
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
    BaseResultModel<IPage<ResBillType>> page(Page<ResBillType> page, ReqBillTypeQuery req);
    
}