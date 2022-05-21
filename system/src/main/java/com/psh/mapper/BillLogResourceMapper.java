package com.psh.mapper;

import com.psh.entity.BillLogResource;
import com.psh.entity.request.ReqBillLogResourceQuery;
import com.psh.entity.response.ResBillLogResource;
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
* @Date 2022-05-18
* @Time 21:29:52
*日志资源持久层
*/
@Mapper
public interface BillLogResourceMapper extends BaseMapper<BillLogResource>  {

    /**
    * 分页查询
    */
    List<ResBillLogResource> listByPage(Page<ResBillLogResource> page, @Param("param") ReqBillLogResourceQuery req);
}
