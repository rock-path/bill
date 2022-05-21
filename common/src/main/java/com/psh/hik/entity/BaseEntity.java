package com.psh.hik.entity;



import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
public class BaseEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "id")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "ctime")
    private String ctime;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记", name = "deleted")
    @TableField(value = "DELETED",fill = FieldFill.INSERT)
    private String deleted;


    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", name = "crname")
    @TableField(value = "CRNAME",fill = FieldFill.INSERT)
    private String crname;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "创建人", name = "chname")
    @TableField(value = "CHNAME",fill = FieldFill.INSERT_UPDATE)
    private String chname;
}
