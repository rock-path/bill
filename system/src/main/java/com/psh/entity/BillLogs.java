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
 * 操作日志实体类
 *
 * @author psh
 * @since 2022-05-19 20:21:34
 */
@Data
@ApiModel(value = "BillLogs对象", description = "操作日志实体对象")
public class BillLogs extends BaseEntity implements Serializable {

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记", name = "deleted")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String deleted;

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid", name = "uuid")
    private String uuid;

    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id", name = "logResourceId")
    private Long logResourceId;

    /**
     * 之前的数据
     */
    @ApiModelProperty(value = "之前的数据", name = "before")
    private String before;

    /**
     * 之后的数据
     */
    @ApiModelProperty(value = "之后的数据", name = "after")
    private String after;

    /**
     * 变更的数据
     */
    @ApiModelProperty(value = "变更的数据", name = "logs")
    private String logs;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip", name = "ip")
    private String ip;

    /**
     * 操作用户
     */
    @ApiModelProperty(value = "操作用户", name = "user")
    @TableField(value = "USER",fill = FieldFill.INSERT_UPDATE)
    private String user;

}

