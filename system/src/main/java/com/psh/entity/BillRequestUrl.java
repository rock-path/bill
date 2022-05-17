package com.psh.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.apache.ibatis.type.JdbcType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 访问记录实体类
 *
 * @author makejava
 * @since 2022-05-15 20:30:49
 */
@Data
@ApiModel(value = "BillRequestUrl对象", description = "访问记录实体对象")
public class BillRequestUrl {

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid", name = "uuid")
    private String uuid;

    /**
     * 访问路径
     */
    @ApiModelProperty(value = "访问路径", name = "url")
    private String url;

    /**
     * 访问时间
     */
    @ApiModelProperty(value = "访问时间", name = "reqTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reqTime;

    /**
     * 访问ip
     */
    @ApiModelProperty(value = "访问ip", name = "ip")
    private String ip;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记", name = "deleted")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String deleted;

    public static BillRequestUrl build(String uuid,String url){
        BillRequestUrl billRequestUrl =new BillRequestUrl();
        billRequestUrl.setUrl(url);
        billRequestUrl.setUuid(uuid);
        billRequestUrl.setReqTime(new Date());
        billRequestUrl.setDeleted("0");
        return billRequestUrl;
    }

}

