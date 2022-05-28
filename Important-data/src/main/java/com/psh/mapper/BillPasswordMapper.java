package com.psh.mapper;

import com.psh.entity.BillPassword;
import com.psh.entity.request.ReqBillPasswordQuery;
import com.psh.entity.response.ResBillPassword;
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
* @Date 2022-05-28
* @Time 10:45:31
*账号密码持久层
*/
@Mapper
public interface BillPasswordMapper extends BaseMapper<BillPassword>  {

    /**
    * 分页查询
    */
    List<ResBillPassword> listByPage(Page<ResBillPassword> page, @Param("param") ReqBillPasswordQuery req);
}
