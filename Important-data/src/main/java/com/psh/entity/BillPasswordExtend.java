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
 * 账号密码富文本大附件实体类
 *
 * @author psh
 * @since 2022-05-28 21:47:07
 */
@Data
@ApiModel(value = "BillPasswordExtend对象", description = "账号密码富文本大附件实体对象")
public class BillPasswordExtend  extends BaseEntity implements Serializable {

    /**
     * 密码id
     */
    @ApiModelProperty(value = "密码id", name = "passwordId")
    private String passwordId;

    /**
     * 描述，16M上限
     */
    @ApiModelProperty(value = "描述，16M上限", name = "described")
    private String described;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件", name = "attachment")
    private String attachment;


}

