package com.psh.service.impl;

import com.psh.mapper.ImportantEventsMapper;
import com.psh.service.ImportantEventsService;
import com.psh.entity.ImportantEvents;
import com.psh.entity.request.ReqImportantEventsAdd;
import com.psh.entity.request.ReqImportantEventsUpdate;
import com.psh.entity.request.ReqImportantEventsQuery;
import com.psh.entity.response.ResImportantEvents;
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
public class ImportantEventsServiceImpl extends ServiceImpl<ImportantEventsMapper, ImportantEvents> implements ImportantEventsService {

    @Autowired
    private ImportantEventsMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel getOneById(Long id) {
        ImportantEvents entity = baseMapper.selectById(id);
        if (null == entity) {
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        ResImportantEvents res = new ResImportantEvents();
        BeanUtil.copyProperties(entity, res);
        return BaseResultModel.success(res);
    }

    @Override
    @Transactional
    public BaseResultModel insert(ReqImportantEventsAdd req) {
        ImportantEvents entity = new ImportantEvents();
        BeanUtil.copyProperties(req, entity);
        if (!this.save(entity)) {
            throw new BaseException(ResultStatus.INSERT_FAIL.getCode(), ResultStatus.INSERT_FAIL.getMessage());
        }
        return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel update(Long id, ReqImportantEventsUpdate req) {
        ImportantEvents exist = baseMapper.selectById(id);
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
    public BaseResultModel<IPage<ResImportantEvents>> page(Page<ResImportantEvents> page, ReqImportantEventsQuery req) {
        List<ResImportantEvents> res = mapper.listByPage(page, req);
        page.setRecords(res);
        return BaseResultModel.success(page);
    }
}