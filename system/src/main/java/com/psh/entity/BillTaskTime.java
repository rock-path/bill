package com.psh.entity;



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
 * 动态定时任务执行时间实体类
 *
 * @author psh
 * @since 2022-06-12 14:09:26
 */
@Data
@ApiModel(value = "BillTaskTime对象", description = "动态定时任务执行时间实体对象")
public class BillTaskTime {
    
        /**
    * 定时任务执行时间
    */   
    @ApiModelProperty(value="定时任务执行时间",name="corn")
                        private String corn;
        
        /**
    * 删除标记
    */   
    @ApiModelProperty(value="删除标记",name="deleted")
            @TableLogic
    @TableField(value = "DELETED",fill = FieldFill.INSERT,jdbcType = JdbcType.VARCHAR)
            private String deleted;
                        
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

