package com.psh.log;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Data
public class Logs {


    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记", name = "deleted")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String deleted;

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid", name = "uuid")
    private String uuid;

    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id", name = "logResourceId")
    private Long logResourceId;

    /**
     * 之前的数据
     */
    @ApiModelProperty(value = "之前的数据", name = "before")
    private String before;

    /**
     * 之后的数据
     */
    @ApiModelProperty(value = "之后的数据", name = "after")
    private String after;

    /**
     * 变更的数据
     */
    @ApiModelProperty(value = "变更的数据", name = "logs")
    private String logs;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip", name = "ip")
    private String ip;

    /**
     * 操作用户
     */
    @ApiModelProperty(value = "操作用户", name = "user")
    private String user;

    public static Logs build(String ip, String recordName, long logId) {
        Logs logs = new Logs();
        logs.setUuid(UUID.randomUUID().toString().replace("-",""));
        logs.setIp(ip);
        logs.setUser(recordName);
        logs.setLogResourceId(logId);
        logs.setDeleted("0");
        return logs;
    }


}
