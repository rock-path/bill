package com.psh.mapper;

import com.psh.entity.BillIncome;
import com.psh.entity.request.ReqBillIncomeQuery;
import com.psh.entity.response.ResBillIncome;
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
* @Date 2022-01-29
* @Time 21:39:20
*收入表持久层
*/
@Mapper
public interface BillIncomeMapper extends BaseMapper<BillIncome>  {

    /**
    * 分页查询
    */
    List<ResBillIncome> listByPage(Page<ResBillIncome> page, @Param("param") ReqBillIncomeQuery req);
}