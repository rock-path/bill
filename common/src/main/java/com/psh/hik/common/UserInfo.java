package com.psh.hik.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
public class UserInfo {
    /**
     * 用户中文名
     */
    private String userNotesName;
    /**
     * 用户域账号（OA）
     */
    private String userShortName;
    /**
     * 员工编号（OA）
     */
    private String userEmpid;
    /**
     * 员工部门编号（OA）
     */
    private String userDeptCode;
    /**
     * 员工部门名称（OA）
     */
    private String userDeptName;
    /**
     * 员工部门名径（OA）
     */
    private String userDeptPath;
    /**
     * 员工部门名称路径（OA）
     */
    private String userDeptPathName;
    /**
     * 员工账号状态（OA）quit（离职），frozen（冻结），active（有效）
     */
    private String userStatus;
    /**
     * 0-超级管理员，1-租户管理员,2-普通管理员,null或空字符串为普通用户
     */
    private Integer userType;
    /**
     * 用户岗位级别
     */
    private String userPostlevel;
    /**
     * 邮箱
     */
    private String userInmail;
    /**
     * 直接主管
     */
    private String userZjld;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 其它属性
     */
    private Map<String, Object> properties;
    /**
     * 角色列表
     */
    private List<RoleInfo> roleList;

    public List<String> roleIds() {
        if (this.roleList == null) {
            return new ArrayList<>();
        }

        return this.roleList.stream().map(a -> {
            return a.getRoleId();
        }).collect(Collectors.toList());
    }

    public Object get(String key) {
        return properties.get(key);
    }

    public void put(String key, String value) {
        properties.put(key, value);
    }
}
