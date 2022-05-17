package com.psh.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 访问记录更新实体类
 *
 * @author makejava
 * @since 2022-05-15 20:30:50
 */
@Data
@ApiModel(value = "BillRequestUrl更新对象", description = "访问记录更新实体对象")
public class ReqBillRequestUrlUpdate implements Serializable {

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

}
