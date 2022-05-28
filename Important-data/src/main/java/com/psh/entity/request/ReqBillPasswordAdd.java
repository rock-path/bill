package com.psh.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 账号密码新增实体类
 *
 * @author psh
 * @since 2022-05-28 10:45:28
 */
@Data
@ApiModel(value = "BillPassword新增对象", description = "账号密码新增实体对象")
public class ReqBillPasswordAdd implements Serializable {

    /**
     * 软件名
     */
    @ApiModelProperty(value = "软件名", name = "softwareName")
    private String softwareName;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "account")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "described")
    private String described;

}
