package com.psh.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

/**
 * 实体类
 *
 * @author makejava
 * @since 2021-11-11 22:31:31
 */
@Data
@ApiModel(value = "Users对象", description = "实体对象")
public class Users {

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "account")
    private String account;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username")
    private String username;

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

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", name = "cname")
    private String cname;

    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除", name = "deleted")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private Boolean deleted;

}