package com.psh.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;

/**
 * 账号密码富文本大附件新增实体类
 *
 * @author psh
 * @since 2022-05-28 21:47:07
 */
@Data
@ApiModel(value = "BillPasswordExtend新增对象", description = "账号密码富文本大附件新增实体对象")
public class ReqBillPasswordExtendAdd implements Serializable {

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
