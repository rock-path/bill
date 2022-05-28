package com.psh.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 实体类
 *
 * @author makejava
 * @since 2021-10-06 20:08:50
 */
@Data
@ApiModel(value = "Users对象", description = "实体对象")
//索引名，分片，副本
@Document(indexName = "user",shards = 3,replicas = 1)
public class Users {
    /**
     * 账号
     */
    private String id;


    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "account")
    @Field(type = FieldType.Keyword)//关键词不分词
    private String account;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username")
    private String username;

    /**
     * 临时添加的，
     */
    @ApiModelProperty(value = "年龄", name = "age")
    private Integer age;


    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    @Field(type = FieldType.Keyword,index = false)//关键词不分词,且不作为索引查询
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
    private String deleted;

}