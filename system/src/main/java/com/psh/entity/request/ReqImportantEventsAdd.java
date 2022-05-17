package com.psh.entity.request;


import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 重要事件新增实体类
 *
 * @author makejava
 * @since 2022-02-19 15:48:52
 */
@Data
@ApiModel(value = "ImportantEvents新增对象", description = "重要事件新增实体对象")
public class ReqImportantEventsAdd implements Serializable {

    /**
     * 事件时间,年月
     */
    @ApiModelProperty(value = "事件时间,年月", name = "importantTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date importantTime;

    /**
     * 事件描述
     */
    @ApiModelProperty(value = "事件描述", name = "importantDescd")
    private String importantDescd;

}