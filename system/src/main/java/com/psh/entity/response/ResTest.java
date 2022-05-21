package com.psh.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
        /**
 * test查询返回实体类
 *
 * @author psh
 * @since 2022-05-15 20:38:05
 */
@Data
@ApiModel(value = "Test查询返回对象", description = "test查询返回实体对象")
public class ResTest implements Serializable{
    
        /**
    * 主键id
    */    
    @ApiModelProperty(value="主键id",name="id")
    private Long id;
         
        /**
    * uuid
    */    
    @ApiModelProperty(value="uuid",name="uuid")
    private String uuid;
     
}
