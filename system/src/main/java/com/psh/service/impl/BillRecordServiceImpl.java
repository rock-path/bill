package com.psh.service.impl;

import com.psh.mapper.BillRecordMapper;
import com.psh.service.BillRecordService;
import com.psh.entity.BillRecord;
import com.psh.entity.request.ReqBillRecordAdd;
import com.psh.entity.request.ReqBillRecordUpdate;
import com.psh.entity.request.ReqBillRecordQuery;
import com.psh.entity.response.ResBillRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psh.hik.domain.BaseResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.psh.hik.common.ResultStatus;
import com.psh.hik.domain.BaseException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillRecordServiceImpl extends ServiceImpl<BillRecordMapper, BillRecord> implements BillRecordService {
    
    @Autowired
    private BillRecordMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel getOneById(Long id) {
        BillRecord entity = baseMapper.selectById(id);
        if(null == entity){
               throw new BaseException(ResultStatus.NO_RECORDS.getCode(),ResultStatus.NO_RECORDS.getMessage());
        }
        ResBillRecord res = new ResBillRecord();
        BeanUtil.copyProperties(entity, res);
        return BaseResultModel.success(res);
    }
    
    @Override
    @Transactional
    public BaseResultModel insert(ReqBillRecordAdd req){
        BillRecord entity = new BillRecord();
        BeanUtil.copyProperties(req, entity);
        if (!this.save(entity)) {
                        throw new BaseException(ResultStatus.INSERT_FAIL.getCode(),ResultStatus.INSERT_FAIL.getMessage());
        }
        return BaseResultModel.success();
    }
    
    @Override
    @Transactional
    public BaseResultModel update(Long id, ReqBillRecordUpdate req){
        BillRecord exist = baseMapper.selectById(id);
        if(null == exist){
                        throw new BaseException(ResultStatus.NO_RECORDS.getCode(),ResultStatus.NO_RECORDS.getMessage());
        }
        BeanUtil.copyProperties(req, exist);

        if (!this.updateById(exist)) {
                       throw new BaseException(ResultStatus.UPDATE_FAIL.getCode(),ResultStatus.UPDATE_FAIL.getMessage());
        }
        return BaseResultModel.success();
        
    }
    
    @Override
    @Transactional
    public BaseResultModel deleteOne(Long id){
        if (!this.removeById(id)) {
                        throw new BaseException(ResultStatus.DELETE_FAIL.getCode(),ResultStatus.DELETE_FAIL.getMessage());
        }
         return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel deleteBatch(List<Long> ids){
        //批量删除
        if (!this.removeByIds(ids)) {
                        throw new BaseException(ResultStatus.DELETE_BATCH_FAIL.getCode(),ResultStatus.DELETE_BATCH_FAIL.getMessage());
        }
        return BaseResultModel.success();
        
        
    }
        
    @Override
    @Transactional(readOnly = true)
    public BaseResultModel<IPage<ResBillRecord>> page(Page<ResBillRecord> page, ReqBillRecordQuery req){
        List<ResBillRecord> res = mapper.listByPage(page,req);
        page.setRecords(res);
        return BaseResultModel.success(page);
    }
}