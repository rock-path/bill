package com.psh.mapper;

import com.psh.entity.BillRequestUrl;
import com.psh.entity.request.ReqBillRequestUrlQuery;
import com.psh.entity.response.ResBillRequestUrl;
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
* @Date 2022-05-15
* @Time 20:30:50
*访问记录持久层
*/
@Mapper
public interface BillRequestUrlMapper extends BaseMapper<BillRequestUrl>  {

    /**
    * 分页查询
    */
    List<ResBillRequestUrl> listByPage(Page<ResBillRequestUrl> page, @Param("param") ReqBillRequestUrlQuery req);
}
