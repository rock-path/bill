package com.psh.mapper;

import com.psh.entity.BillLogs;
import com.psh.entity.request.ReqBillLogsQuery;
import com.psh.entity.response.ResBillLogs;
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
* @Date 2022-05-19
* @Time 20:21:34
*操作日志持久层
*/
@Mapper
public interface BillLogsMapper extends BaseMapper<BillLogs>  {

    /**
    * 分页查询
    */
    List<ResBillLogs> listByPage(Page<ResBillLogs> page, @Param("param") ReqBillLogsQuery req);
}
