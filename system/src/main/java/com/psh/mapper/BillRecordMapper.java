package com.psh.mapper;

import com.psh.entity.BillRecord;
import com.psh.entity.request.ReqBillRecordQuery;
import com.psh.entity.response.ResBillRecord;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import javax.validation.constraints.*;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


/**
* @Author makejava
* @Date 2022-01-28
* @Time 21:26:44
*消费记录表持久层
*/
@Mapper
public interface BillRecordMapper extends BaseMapper<BillRecord>  {

    /**
    * 分页查询
    */
    List<ResBillRecord> listByPage(Page<ResBillRecord> page, @Param("param") ReqBillRecordQuery req);
}