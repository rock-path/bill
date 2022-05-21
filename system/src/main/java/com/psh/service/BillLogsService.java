package com.psh.service;

import com.psh.entity.BillLogs;
import com.psh.entity.request.ReqBillLogsAdd;
import com.psh.entity.request.ReqBillLogsUpdate;
import com.psh.entity.request.ReqBillLogsQuery;
import com.psh.entity.response.ResBillLogs;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.psh.hik.domain.BaseResultModel;

/**
* @Author psh
* @Date 2022-05-19
* @Time 20:21:34
*操作日志服务层
*/
public interface BillLogsService extends IService<BillLogs>  {

    /**
    * 根据主键查询
    */
    BaseResultModel getOneById(Long id);
    
    /**
    * 新增
    */
    BaseResultModel insert(ReqBillLogsAdd req);
    
    /**
    * 修改
    */
    BaseResultModel update(Long id, ReqBillLogsUpdate req);
    
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
    BaseResultModel<IPage<ResBillLogs>> page(Page<ResBillLogs> page, ReqBillLogsQuery req);
    
}
