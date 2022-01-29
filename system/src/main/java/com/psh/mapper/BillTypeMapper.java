package com.psh.mapper;

import com.psh.entity.BillType;
import com.psh.entity.request.ReqBillTypeQuery;
import com.psh.entity.response.ResBillType;
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
* @Time 21:26:56
*消费类型表持久层
*/
@Mapper
public interface BillTypeMapper extends BaseMapper<BillType>  {

    /**
    * 分页查询
    */
    List<ResBillType> listByPage(Page<ResBillType> page, @Param("param") ReqBillTypeQuery req);
}