package com.psh.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 账号密码富文本大附件更新实体类
 *
 * @author psh
 * @since 2022-05-28 21:47:07
 */
@Data
@ApiModel(value = "BillPasswordExtend更新对象", description = "账号密码富文本大附件更新实体对象")
public class ReqBillPasswordExtendUpdate implements Serializable {

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
