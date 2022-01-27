package com.psh.mapper;

import com.psh.entity.TscmUser;
import com.psh.entity.request.ReqTscmUserQuery;
import com.psh.entity.response.ResTscmUser;
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
* @Date 2022-01-27
* @Time 21:07:43
*用户表持久层
*/
@Mapper
public interface TscmUserMapper extends BaseMapper<TscmUser>  {

    /**
    * 分页查询
    */
    List<ResTscmUser> listByPage(Page<ResTscmUser> page, @Param("param") ReqTscmUserQuery req);
}