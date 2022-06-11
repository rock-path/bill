package com.psh.entity;


import com.psh.hik.entity.BaseEntity;
import com.psh.util.IdGeneratorUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.val;
import org.apache.ibatis.type.JdbcType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.IdGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * 消费记录表实体类
 *
 * @author psh
 * @since 2022-06-11 15:30:17
 */
@Data
@ApiModel(value = "BillRecord对象", description = "消费记录表实体对象")
public class BillRecord extends BaseEntity implements Serializable {

    /**
     * 消费类型id
     */
    @ApiModelProperty(value = "消费类型id", name = "tId")
    private String tId;

    /**
     * 记录id
     */
    @ApiModelProperty(value = "记录id", name = "rId")
    private String rId;

    /**
     * 消费时间
     */
    @ApiModelProperty(value = "消费时间", name = "rTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rTime;

    /**
     * 消费金额
     */
    @ApiModelProperty(value = "消费金额", name = "rNumber")
    private String rNumber;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记", name = "deleted")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String deleted;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "descd")
    private String descd;

    public static BillRecord build(String tId,Date rTime,String rNumber,String descd) {
        BillRecord billRecord = new BillRecord();
        billRecord.setDeleted("0");
        IdGeneratorUtil idGeneratorUtil = new IdGeneratorUtil();
        billRecord.setRId(idGeneratorUtil.nextId()+"");
        billRecord.setTId(tId);
        billRecord.setRTime(rTime);
        billRecord.setRNumber(rNumber);
        billRecord.setDescd(descd);
        return billRecord;
    }

}

