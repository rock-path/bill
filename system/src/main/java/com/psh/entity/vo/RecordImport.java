package com.psh.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecordImport implements Serializable {

    /**
     * 消费类型id
     */
    //设置excel表头名称
    @ExcelProperty(value = "t_id",index = 0)
    @ApiModelProperty(value = "消费类型id", name = "tId")
    private String t_id;


//    /**
//     * 消费时间
//     */
//    @ExcelProperty(value = "r_time",index = 1)
//    @ApiModelProperty(value = "消费时间", name = "rTime")
//    private String r_time;

    /**
     * 消费时间
     */
    @ExcelProperty(value = "r_time",index = 1)
    @ApiModelProperty(value = "消费时间", name = "rTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date r_time;

    /**
     * 消费金额
     */
    @ExcelProperty(value = "r_number",index = 2)
    @ApiModelProperty(value = "消费金额", name = "rNumber")
    private String r_number;

    /**
     * 描述
     */
    @ExcelProperty(value = "descd",index = 3)
    @ApiModelProperty(value="描述",name="desc")
    private String descd;


}
