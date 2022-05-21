package com.psh.log;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

@Data
public class LogResource {

    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "id")
    private Long id;

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
