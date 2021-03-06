package com.psh.entity.request;


import com.mysql.cj.jdbc.Blob;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 账号密码更新实体类
 *
 * @author psh
 * @since 2022-05-28 10:45:32
 */
@Data
@ApiModel(value = "BillPassword更新对象", description = "账号密码更新实体对象")
public class ReqBillPasswordUpdate implements Serializable {

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
