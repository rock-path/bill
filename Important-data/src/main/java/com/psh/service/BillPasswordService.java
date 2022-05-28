package com.psh.service;

import com.psh.entity.BillPassword;
import com.psh.entity.request.ReqBillPasswordAdd;
import com.psh.entity.request.ReqBillPasswordUpdate;
import com.psh.entity.request.ReqBillPasswordQuery;
import com.psh.entity.response.ResBillPassword;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.psh.hik.domain.BaseResultModel;

/**
* @Author psh
* @Date 2022-05-28
* @Time 10:31:34
*账号密码服务层
*/
public interface BillPasswordService extends IService<BillPassword>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillPasswordAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillPasswordUpdate req);
    
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
    BaseResultModel<IPage<ResBillPassword>> page(Page<ResBillPassword> page, ReqBillPasswordQuery req);
    
}
