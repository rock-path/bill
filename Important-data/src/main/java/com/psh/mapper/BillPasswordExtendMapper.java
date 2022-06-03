package com.psh.mapper;

import com.psh.entity.BillPasswordExtend;
import com.psh.entity.request.ReqBillPasswordExtendQuery;
import com.psh.entity.response.ResBillPasswordExtend;
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
* @Time 21:47:07
*账号密码富文本大附件持久层
*/
@Mapper
public interface BillPasswordExtendMapper extends BaseMapper<BillPasswordExtend>  {

    /**
    * 分页查询
    */
    List<ResBillPasswordExtend> listByPage(Page<ResBillPasswordExtend> page, @Param("param") ReqBillPasswordExtendQuery req);
}
