package com.psh.entity;


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

import java.io.Serializable;

/**
 * 日志资源实体类
 *
 * @author psh
 * @since 2022-05-18 21:29:52
 */
@Data
@ApiModel(value = "BillLogResource对象", description = "日志资源实体对象")
public class BillLogResource extends BaseEntity implements Serializable {

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记", name = "deleted")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String deleted;

    /**
     * 资源
     */
    @ApiModelProperty(value = "资源", name = "resource")
    private String resource;

    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式", name = "requestMode")
    private String requestMode;

    /**
     * 动作
     */
    @ApiModelProperty(value = "动作", name = "action")
    private String action;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "described")
    private String described;

    /**
     * 实体
     */
    @ApiModelProperty(value = "实体", name = "entity")
    private String entity;


    /**
     * 模块
     */
    @ApiModelProperty(value = "模块", name = "modules")
    private String modules;


    /**
     * 资源所在的controller
     */
    @ApiModelProperty(value = "资源所在的controller", name = "cla")
    private String cla;

}

