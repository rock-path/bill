package com.psh.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
            import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 操作日志查询返回实体类
 *
 * @author psh
 * @since 2022-05-19 20:21:34
 */
@Data
@ApiModel(value = "BillLogs查询返回对象", description = "操作日志查询返回实体对象")
public class ResBillLogs implements Serializable{
    
        /**
    * 主键id
    */    
    @ApiModelProperty(value="主键id",name="id")
    private Long id;
                             
        /**
    * uuid
    */    
    @ApiModelProperty(value="uuid",name="uuid")
    private String uuid;
         
        /**
    * 资源id
    */    
    @ApiModelProperty(value="资源id",name="logResourceId")
    private Long logResourceId;
         
        /**
    * 之前的数据
    */    
    @ApiModelProperty(value="之前的数据",name="before")
    private String before;
         
        /**
    * 之后的数据
    */    
    @ApiModelProperty(value="之后的数据",name="after")
    private String after;
         
        /**
    * 变更的数据
    */    
    @ApiModelProperty(value="变更的数据",name="logs")
    private String logs;
         
        /**
    * ip
    */    
    @ApiModelProperty(value="ip",name="ip")
    private String ip;
         
        /**
    * 操作用户
    */    
    @ApiModelProperty(value="操作用户",name="user")
    private String user;
     
}
