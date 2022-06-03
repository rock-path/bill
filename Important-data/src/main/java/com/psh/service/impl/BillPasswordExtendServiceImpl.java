package com.psh.service.impl;

import com.psh.hik.common.ResultStatus;
import com.psh.hik.domain.BaseException;
import com.psh.hik.domain.BaseResultModel;
import com.psh.mapper.BillPasswordExtendMapper;
import com.psh.service.BillPasswordExtendService;
import com.psh.entity.BillPasswordExtend;
import com.psh.entity.request.ReqBillPasswordExtendAdd;
import com.psh.entity.request.ReqBillPasswordExtendUpdate;
import com.psh.entity.request.ReqBillPasswordExtendQuery;
import com.psh.entity.response.ResBillPasswordExtend;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BillPasswordExtendServiceImpl extends ServiceImpl<BillPasswordExtendMapper, BillPasswordExtend> implements BillPasswordExtendService {

    @Autowired
    private BillPasswordExtendMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel getOneById(Long id) {
        BillPasswordExtend entity = baseMapper.selectById(id);
        if (null == entity) {
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());

        }
        ResBillPasswordExtend res = new ResBillPasswordExtend();
        BeanUtil.copyProperties(entity, res);
        return BaseResultModel.success(res);
    }

    @Override
    @Transactional
    public BaseResultModel insert(ReqBillPasswordExtendAdd req) {
        BillPasswordExtend entity = new BillPasswordExtend();
        BeanUtil.copyProperties(req, entity);
        if (!this.save(entity)) {
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel update(Long id, ReqBillPasswordExtendUpdate req) {
        BillPasswordExtend exist = baseMapper.selectById(id);
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
    public BaseResultModel<IPage<ResBillPasswordExtend>> page(Page<ResBillPasswordExtend> page, ReqBillPasswordExtendQuery req) {
        List<ResBillPasswordExtend> res = mapper.listByPage(page, req);
        if (res == null || res.size() <= 0) {
            return BaseResultModel.success();
        }
        page.setRecords(res);
        return BaseResultModel.success(page);
    }
}
