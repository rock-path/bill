package com.psh.service.impl;

import com.psh.hik.common.ResultStatus;
import com.psh.hik.domain.BaseException;
import com.psh.hik.domain.BaseResultModel;
import com.psh.mapper.BillLogsMapper;
import com.psh.service.BillLogsService;
import com.psh.entity.BillLogs;
import com.psh.entity.request.ReqBillLogsAdd;
import com.psh.entity.request.ReqBillLogsUpdate;
import com.psh.entity.request.ReqBillLogsQuery;
import com.psh.entity.response.ResBillLogs;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillLogsServiceImpl extends ServiceImpl<BillLogsMapper, BillLogs> implements BillLogsService {
    
    @Autowired
    private BillLogsMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel getOneById(Long id) {
        BillLogs entity = baseMapper.selectById(id);
        if(null == entity){
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        ResBillLogs res = new ResBillLogs();
        BeanUtil.copyProperties(entity, res);
        return BaseResultModel.success(res);
    }
    
    @Override
    @Transactional
    public BaseResultModel insert(ReqBillLogsAdd req){
        BillLogs entity = new BillLogs();
        BeanUtil.copyProperties(req, entity);
        if (!this.save(entity)) {
            throw new BaseException(ResultStatus.INSERT_FAIL.getCode(), ResultStatus.INSERT_FAIL.getMessage());
        }
        return BaseResultModel.success();
    }
    
    @Override
    @Transactional
    public BaseResultModel update(Long id, ReqBillLogsUpdate req){
        BillLogs exist = baseMapper.selectById(id);
        if(null == exist){
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        BeanUtil.copyProperties(req, exist);

        if (!this.updateById(exist)) {
            throw new BaseException(ResultStatus.UPDATE_FAIL.getCode(), ResultStatus.UPDATE_FAIL.getMessage());
        }
        return BaseResultModel.success();
        
    }
    
    @Override
    @Transactional
    public BaseResultModel deleteOne(Long id){
        if (!this.removeById(id)) {
            throw new BaseException(ResultStatus.DELETE_FAIL.getCode(), ResultStatus.DELETE_FAIL.getMessage());
        }
         return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel deleteBatch(List<Long> ids){
        //批量删除
        if (!this.removeByIds(ids)) {
            throw new BaseException(ResultStatus.DELETE_BATCH_FAIL.getCode(), ResultStatus.DELETE_BATCH_FAIL.getMessage());
        }
        return BaseResultModel.success();
        
        
    }
        
    @Override
    @Transactional(readOnly = true)
    public BaseResultModel<IPage<ResBillLogs>> page(Page<ResBillLogs> page, ReqBillLogsQuery req){
        List<ResBillLogs> res = mapper.listByPage(page,req);
        page.setRecords(res);
        return BaseResultModel.success(page);
    }
}
