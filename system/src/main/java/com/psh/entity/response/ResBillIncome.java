package com.psh.entity.response;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
        import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 收入表查询返回实体类
 *
 * @author makejava
 * @since 2022-01-29 21:39:20
 */
@Data
@ApiModel(value = "BillIncome查询返回对象", description = "收入表查询返回实体对象")
public class ResBillIncome implements Serializable{
    
        /**
    * 主键id
    */    
    @ApiModelProperty(value="主键id",name="id")
    private Long id;
         
        /**
    * 收入的时间,年月
    */    
    @ApiModelProperty(value="收入的时间,年月",name="iTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date iTime;
         
        /**
    * 金额
    */    
    @ApiModelProperty(value="金额",name="iNumber")
    private String iNumber;
         
        /**
    * 描述
    */    
    @ApiModelProperty(value="描述",name="descd")
    private String descd;
                         
}