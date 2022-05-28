package com.psh.service.impl;

import com.psh.entity.vo.BillPasswordEncryption;
import com.psh.hik.common.ResultStatus;
import com.psh.hik.domain.BaseException;
import com.psh.hik.domain.BaseResultModel;
import com.psh.mapper.BillPasswordMapper;
import com.psh.service.BillPasswordService;
import com.psh.entity.BillPassword;
import com.psh.entity.request.ReqBillPasswordAdd;
import com.psh.entity.request.ReqBillPasswordUpdate;
import com.psh.entity.request.ReqBillPasswordQuery;
import com.psh.entity.response.ResBillPassword;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psh.util.EntityUtils;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BillPasswordServiceImpl extends ServiceImpl<BillPasswordMapper, BillPassword> implements BillPasswordService {

    @Autowired
    private BillPasswordMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResultModel getOneById(Long id) {
        BillPassword entity = baseMapper.selectById(id);
        if (null == entity) {
            throw new BaseException(ResultStatus.NO_RECORDS.getCode(), ResultStatus.NO_RECORDS.getMessage());
        }
        ResBillPassword res = new ResBillPassword();
        BeanUtil.copyProperties(entity, res);
        return BaseResultModel.success(res);
    }

    @Value("${aes.password}")
    private String key;

    @Override
    @Transactional
    public BaseResultModel insert(ReqBillPasswordAdd req) {
        BillPassword entity = new BillPassword();
        BeanUtil.copyProperties(req, entity);
        //数据校验
        BaseResultModel check = EntityUtils.check(entity);
        if (check != null) {
            return check;
        }
        //数据加密
        EntityUtils.encryption(entity, key);
        if (!this.save(entity)) {
            throw new BaseException(ResultStatus.INSERT_FAIL.getCode(), ResultStatus.INSERT_FAIL.getMessage());
        }
        return BaseResultModel.success();
    }

    @Override
    @Transactional
    public BaseResultModel update(Long id, ReqBillPasswordUpdate req) {
        BillPassword exist = baseMapper.selectById(id);
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
    public BaseResultModel<IPage<ResBillPassword>> page(Page<ResBillPassword> page, ReqBillPasswordQuery req) {
        //判断是否需要用加密的字段来查询
        BillPasswordEncryption bi = new BillPasswordEncryption();
        BeanUtils.copyProperties(req, bi);
        Map<String, String> map = EntityUtils.isNotBlank(bi);
        //不需要通过加密字段来查询
        if (map == null) {
            List<ResBillPassword> res = mapper.listByPage(page, req);
            //解密
            if (res != null && res.size() > 0) {
                for (ResBillPassword re : res) {
                    EntityUtils.decryption(re, key);
                }
            }
            page.setRecords(res);
            return BaseResultModel.success(page);
        }
        //分页
        Long pageNum = page.getCurrent();
        Long pageSize = page.getSize();

        //筛选

        return null;
    }
}
