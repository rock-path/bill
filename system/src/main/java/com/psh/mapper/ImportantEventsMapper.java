package com.psh.mapper;

import com.psh.entity.ImportantEvents;
import com.psh.entity.request.ReqImportantEventsQuery;
import com.psh.entity.response.ResImportantEvents;
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
* @Date 2022-02-19
* @Time 15:48:52
*重要事件持久层
*/
@Mapper
public interface ImportantEventsMapper extends BaseMapper<ImportantEvents>  {

    /**
    * 分页查询
    */
    List<ResImportantEvents> listByPage(Page<ResImportantEvents> page, @Param("param") ReqImportantEventsQuery req);
}