package com.psh.entity;


import java.util.Date;

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
 * 收入表实体类
 *
 * @author makejava
 * @since 2022-01-29 21:39:19
 */
@Data
@ApiModel(value = "BillIncome对象", description = "收入表实体对象")
public class BillIncome {

    /**
     * 收入的时间,年月
     */
    @ApiModelProperty(value = "收入的时间,年月", name = "iTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date iTime;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", name = "iNumber")
    private  String iNumber;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "descd")
    private  String descd;

}