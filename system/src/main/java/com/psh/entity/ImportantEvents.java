package com.psh.entity;


import java.io.Serializable;
import java.util.Date;

import com.psh.hik.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.apache.ibatis.type.JdbcType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 重要事件实体类
 *
 * @author makejava
 * @since 2022-02-19 15:48:52
 */
@Data
@ApiModel(value = "ImportantEvents对象", description = "重要事件实体对象")
public class ImportantEvents extends BaseEntity implements Serializable {

    /**
     * 事件时间,年月
     */
    @ApiModelProperty(value = "事件时间,年月", name = "importantTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date importantTime;

    /**
     * 事件描述
     */
    @ApiModelProperty(value = "事件描述", name = "importantDescd")
    private String importantDescd;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记", name = "deleted")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String deleted;

    /**
     * 事件概要
     */
    @ApiModelProperty(value = "事件概要", name = "summary")
    private String summary;

}