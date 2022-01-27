package com.psh.entity.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
                                        import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 用户表查询实体类
 *
 * @author makejava
 * @since 2022-01-27 21:07:43
 */
@Data
@ApiModel(value = "TscmUser查询对象", description = "用户表查询实体对象")
public class ReqTscmUserQuery implements Serializable{
    
        /**
    * 账号就是域账号
    */    
    @ApiModelProperty(value="账号就是域账号",name="account")
    private String account;
         
        /**
    * 责任人,用户名
    */    
    @ApiModelProperty(value="责任人,用户名",name="username")
    private String username;
         
        /**
    * 用户部门
    */    
    @ApiModelProperty(value="用户部门",name="userDepartment")
    private String userDepartment;
         
        /**
    * 用户邮箱
    */    
    @ApiModelProperty(value="用户邮箱",name="userMailbox")
    private String userMailbox;
         
        /**
    * 描述
    */    
    @ApiModelProperty(value="描述",name="userDescription")
    private String userDescription;
         
        /**
    * 用户是否登录
    */    
    @ApiModelProperty(value="用户是否登录",name="login")
    private Boolean login;
                                 
        /**
    * 用户判断当前用户是否重复登录
    */    
    @ApiModelProperty(value="用户判断当前用户是否重复登录",name="sessionId")
    private String sessionId;
     
}