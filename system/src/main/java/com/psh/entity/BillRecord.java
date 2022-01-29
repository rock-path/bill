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
 * 消费记录表实体类
 *
 * @author makejava
 * @since 2022-01-28 21:26:44
 */
@Data
@ApiModel(value = "BillRecord对象", description = "消费记录表实体对象")
public class BillRecord {
    
        /**
    * 消费类型id
    */   
    @ApiModelProperty(value="消费类型id",name="tId")
                        private String tId;
        
        /**
    * 记录id
    */   
    @ApiModelProperty(value="记录id",name="rId")
                        private String rId;
        
        /**
    * 消费时间
    */   
    @ApiModelProperty(value="消费时间",name="rTime")
                        private String rTime;
        
        /**
    * 消费金额
    */   
    @ApiModelProperty(value="消费金额",name="rNumber")
                        private String rNumber;
        
        /**
    * 描述
    */   
    @ApiModelProperty(value="描述",name="desc")
                        private String desc;
        
        /**
    * 删除标记
    */   
    @ApiModelProperty(value="删除标记",name="deleted")
            @TableLogic
    @TableField(value = "DELETED",fill = FieldFill.INSERT,jdbcType = JdbcType.VARCHAR)
            private String deleted;
                        
        /**
    * 用户判断当前用户是否重复登录
    */   
    @ApiModelProperty(value="用户判断当前用户是否重复登录",name="sessionId")
                        private String sessionId;
    
}