package com.psh.entity.request;




 
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
        /**
 * test新增实体类
 *
 * @author psh
 * @since 2022-05-15 20:38:05
 */
@Data
@ApiModel(value = "Test新增对象", description = "test新增实体对象")
public class ReqTestAdd implements Serializable{
    
        /**
    * uuid
    */    
    @ApiModelProperty(value="uuid",name="uuid")
    private String uuid;
     
}
