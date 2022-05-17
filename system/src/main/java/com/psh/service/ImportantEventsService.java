package com.psh.service;

import com.psh.entity.ImportantEvents;
import com.psh.entity.request.ReqImportantEventsAdd;
import com.psh.entity.request.ReqImportantEventsUpdate;
import com.psh.entity.request.ReqImportantEventsQuery;
import com.psh.entity.response.ResImportantEvents;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.psh.hik.domain.BaseResultModel;
import java.util.List;

/**
* @Author makejava
* @Date 2022-02-19
* @Time 15:48:52
*重要事件服务层
*/
public interface ImportantEventsService extends IService<ImportantEvents>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqImportantEventsAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqImportantEventsUpdate req);
    
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
    BaseResultModel<IPage<ResImportantEvents>> page(Page<ResImportantEvents> page, ReqImportantEventsQuery req);
    
}