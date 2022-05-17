package com.psh.service;

import com.psh.entity.BillRequestUrl;
import com.psh.entity.request.ReqBillRequestUrlAdd;
import com.psh.entity.request.ReqBillRequestUrlUpdate;
import com.psh.entity.request.ReqBillRequestUrlQuery;
import com.psh.entity.response.ResBillRequestUrl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.psh.hik.domain.BaseResultModel;
import java.util.List;
import com.github.pagehelper.PageInfo;

/**
* @Author makejava
* @Date 2022-05-15
* @Time 20:30:50
*访问记录服务层
*/
public interface BillRequestUrlService extends IService<BillRequestUrl>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillRequestUrlAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillRequestUrlUpdate req);
    
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
    BaseResultModel<IPage<ResBillRequestUrl>> page(Page<ResBillRequestUrl> page, ReqBillRequestUrlQuery req);
    
}
