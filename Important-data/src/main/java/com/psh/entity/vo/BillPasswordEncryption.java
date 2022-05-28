package com.psh.entity.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

/**
 * 账号密码实体类
 *
 * @author psh
 * @since 2022-05-28 10:45:31
 */
@Data
@ApiModel(value = "BillPassword对象", description = "账号密码实体对象")
public class BillPasswordEncryption {

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

