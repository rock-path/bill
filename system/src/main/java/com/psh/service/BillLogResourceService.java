package com.psh.service;

import com.psh.entity.BillLogResource;
import com.psh.entity.request.ReqBillLogResourceAdd;
import com.psh.entity.request.ReqBillLogResourceUpdate;
import com.psh.entity.request.ReqBillLogResourceQuery;
import com.psh.entity.response.ResBillLogResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.psh.hik.domain.BaseResultModel;

/**
* @Author psh
* @Date 2022-05-18
* @Time 21:29:52
*日志资源服务层
*/
public interface BillLogResourceService extends IService<BillLogResource>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillLogResourceAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillLogResourceUpdate req);
    
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
    BaseResultModel<IPage<ResBillLogResource>> page(Page<ResBillLogResource> page, ReqBillLogResourceQuery req);
    
}
