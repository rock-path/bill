package com.psh.service;

import com.psh.entity.BillPasswordExtend;
import com.psh.entity.request.ReqBillPasswordExtendAdd;
import com.psh.entity.request.ReqBillPasswordExtendUpdate;
import com.psh.entity.request.ReqBillPasswordExtendQuery;
import com.psh.entity.response.ResBillPasswordExtend;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.psh.hik.domain.BaseResultModel;

/**
* @Author psh
* @Date 2022-05-28
* @Time 21:47:07
*账号密码富文本大附件服务层
*/
public interface BillPasswordExtendService extends IService<BillPasswordExtend>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillPasswordExtendAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillPasswordExtendUpdate req);
    
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
    BaseResultModel<IPage<ResBillPasswordExtend>> page(Page<ResBillPasswordExtend> page, ReqBillPasswordExtendQuery req);
    
}
