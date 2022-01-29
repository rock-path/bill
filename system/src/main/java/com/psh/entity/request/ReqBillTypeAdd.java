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
 * 消费类型表新增实体类
 *
 * @author makejava
 * @since 2022-01-28 21:26:56
 */
@Data
@ApiModel(value = "BillType新增对象", description = "消费类型表新增实体对象")
public class ReqBillTypeAdd implements Serializable{
    
        /**
    * 类型id
    */    
    @ApiModelProperty(value="类型id",name="tId")
    private String tId;
         
        /**
    * 类型名称
    */    
    @ApiModelProperty(value="类型名称",name="tName")
    private String tName;
         
        /**
    * 父类型id
    */    
    @ApiModelProperty(value="父类型id",name="pId")
    private String pId;
         
        /**
    * 描述
    */    
    @ApiModelProperty(value="描述",name="description")
    private String description;
                             
        /**
    * 用户判断当前用户是否重复登录
    */    
    @ApiModelProperty(value="用户判断当前用户是否重复登录",name="sessionId")
    private String sessionId;
     
}