package com.psh.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * test更新实体类
 *
 * @author psh
 * @since 2022-05-15 20:38:05
 */
@Data
@ApiModel(value = "Test更新对象", description = "test更新实体对象")
public class ReqTestUpdate implements Serializable {

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid", name = "uuid")
    private String uuid;

}
