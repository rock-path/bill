package com.psh.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 账号密码查询返回实体类
 *
 * @author psh
 * @since 2022-05-28 10:45:32
 */
@Data
@ApiModel(value = "BillPassword查询返回对象", description = "账号密码查询返回实体对象")
public class ResBillPassword implements Serializable {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", name = "id")
    public Long id;

    /**
     * 软件名
     */
    @ApiModelProperty(value = "软件名", name = "softwareName")
    public String softwareName;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "account")
    public String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    public String password;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "described")
    public String described;

}
