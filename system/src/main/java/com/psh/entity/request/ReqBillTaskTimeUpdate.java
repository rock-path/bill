package com.psh.entity.request;




import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
                import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 动态定时任务执行时间更新实体类
 *
 * @author psh
 * @since 2022-06-12 14:09:27
 */
@Data
@ApiModel(value = "BillTaskTime更新对象", description = "动态定时任务执行时间更新实体对象")
public class ReqBillTaskTimeUpdate implements Serializable{
    
        /**
    * 定时任务执行时间
    */    
    @ApiModelProperty(value="定时任务执行时间",name="corn")
    private String corn;
                             
        /**
    * 任务id
    */    
    @ApiModelProperty(value="任务id",name="taskId")
    private String taskId;
         
        /**
    * 任务描述
    */    
    @ApiModelProperty(value="任务描述",name="taskDesc")
    private String taskDesc;
     
}
