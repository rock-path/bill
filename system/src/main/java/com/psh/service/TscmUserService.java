package com.psh.service;

import com.psh.entity.TscmUser;
import com.psh.entity.request.ReqTscmUserAdd;
import com.psh.entity.request.ReqTscmUserUpdate;
import com.psh.entity.request.ReqTscmUserQuery;
import com.psh.entity.response.ResTscmUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.psh.hik.domain.BaseResultModel;
import java.util.List;

/**
* @Author makejava
* @Date 2022-01-27
* @Time 21:07:44
*用户表服务层
*/
public interface TscmUserService extends IService<TscmUser>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqTscmUserAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqTscmUserUpdate req);
    
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
    BaseResultModel<IPage<ResTscmUser>> page(Page<ResTscmUser> page, ReqTscmUserQuery req);
    
}