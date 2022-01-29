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
 * @since 2022-01-28 21:26:26
 */
@Data
@ApiModel(value = "BillIncome对象", description = "收入表实体对象")
public class BillIncome {
    
        /**
    * 收入的时间,年月
    */   
    @ApiModelProperty(value="收入的时间,年月",name="iTime")
                        private String iTime;
        
        /**
    * 金额
    */   
    @ApiModelProperty(value="金额",name="iNumber")
                        private String iNumber;
        
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