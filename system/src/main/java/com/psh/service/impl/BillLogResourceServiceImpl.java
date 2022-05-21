package com.psh.service.impl;

import com.psh.hik.common.ResultStatus;
import com.psh.hik.domain.BaseException;
import com.psh.hik.domain.BaseResultModel;
import com.psh.mapper.BillLogResourceMapper;
import com.psh.service.BillLogResourceService;
import com.psh.entity.BillLogResource;
import com.psh.entity.request.ReqBillLogResourceAdd;
import com.psh.entity.request.ReqBillLogResourceUpdate;
import com.psh.entity.request.ReqBillLogResourceQuery;
import com.psh.entity.response.ResBillLogResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BillLogResourceServiceImpl extends ServiceImpl<BillLogResourceMapper, BillLogResource> implements BillLogResourceService {

    @Autowired
    private BillLogResourceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel getOneById(Long id) {
        BillLogResource entity = baseMapper.selectById(id);
        if (null == entity) {
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        ResBillLogResource res = new ResBillLogResource();
        BeanUtil.copyProperties(entity, res);
        return BaseResultModel.success(res);
    }

    @Override
    @Transactional
    public BaseResultModel insert(ReqBillLogResourceAdd req) {
        BillLogResource entity = new BillLogResource();
        BeanUtil.copyProperties(req, entity);
        if (!this.save(entity)) {
            throw new BaseException(ResultStatus.INSERT_FAIL.getCode(), ResultStatus.INSERT_FAIL.getMessage());
        }
        return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel update(Long id, ReqBillLogResourceUpdate req) {
        BillLogResource exist = baseMapper.selectById(id);
        if (null == exist) {
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
    public BaseResultModel deleteOne(Long id) {
        if (!this.removeById(id)) {
            throw new BaseException(ResultStatus.DELETE_FAIL.getCode(), ResultStatus.DELETE_FAIL.getMessage());
        }
        return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel deleteBatch(List<Long> ids) {
        //批量删除
        if (!this.removeByIds(ids)) {
            throw new BaseException(ResultStatus.DELETE_BATCH_FAIL.getCode(), ResultStatus.DELETE_BATCH_FAIL.getMessage());
        }
        return BaseResultModel.success();


    }

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel<IPage<ResBillLogResource>> page(Page<ResBillLogResource> page, ReqBillLogResourceQuery req) {
        List<ResBillLogResource> res = mapper.listByPage(page, req);
        page.setRecords(res);
        return BaseResultModel.success(page);
    }
}
