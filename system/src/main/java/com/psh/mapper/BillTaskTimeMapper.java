package com.psh.mapper;

import com.psh.entity.BillTaskTime;
import com.psh.entity.request.ReqBillTaskTimeQuery;
import com.psh.entity.response.ResBillTaskTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import javax.validation.constraints.*;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


/**
* @Author psh
* @Date 2022-06-12
* @Time 13:48:54
*动态定时任务执行时间持久层
*/
@Mapper
public interface BillTaskTimeMapper extends BaseMapper<BillTaskTime>  {

    /**
    * 分页查询
    */
    List<ResBillTaskTime> listByPage(Page<ResBillTaskTime> page, @Param("param") ReqBillTaskTimeQuery req);
}
