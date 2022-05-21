package com.psh.hik.common;


import org.apache.commons.lang3.StringUtils;


public enum ResultStatus {

    SUCCESS("0", "执行成功"),
    ERROR("-1", "系统异常"),
    REQ_REDIRECT("301", "请求已被永久移动到新位置"),
    REQ_GRAMMAR_ERROR("400", "服务器不理解请求的语法"),
    REQ_UN_AUTHORIZED("401", "API访问资源未授权"),
    REQ_REFUSE("403", "访问被服务器拒绝"),
    REQ_NOT_FOUND("404", "请求地址不存在"),
    REQ_EMPTY_PARAMS("406", "请求参数错误或为空"),
    REQ_NOT_OVERTIME("408", "服务器等候请求时超时"),
    SYS_EXCEPTION("500", "系统服务存在异常"),
    SYS_UNENFORCED("501", "服务器不具备完成请求的功能"),
    SYS_GATEWAY_ERROR("502", "服务器作为网关或代理，从后续服务收到了无效的响应"),
    SYS_UNAVAILABLE_ERROR("503", "目前无法使用服务（由于超载或进行停机维护）"),
    SYS_GATEWAY_TIMEOUT("504", "服务器作为网关或代理，未及时从后续服务接收请求"),
    SYS_VERSION_ERROR("505", "HTTP版本不被支持"),
    NO_LOGIN("600", "用户未登录或登录已失效"),
    REPEAT_LOGIN("601", "用户重复登录"),
    NO_CROSS("692", "不允许跨域访问"),
    HTTP_REQ_ERROR("900", "服务请求失败"),
    USER_TENANT_MATCHING_ERROR("902", "用户请求与所属租户不匹配"),
    HTTP_URL_ERROR("903", "请求地址非法或为空"),
    USER_TENANT_LOST_ERROR("904", "用户所属租户序号丢失"),
    VISITOR_URL_RESOURCES_LOST("905", "访客权限资源丢失,请求被拒绝"),
    USER_TENANT_CHANGE_ERROR("906", "请求失败，登陆用户信息已被篡改"),
    NO_RECORDS("1001", "记录不存在"),
    INSERT_FAIL("1002", "新增失败"),
    DELETE_FAIL("1003", "删除失败"),
    UPDATE_FAIL("1004", "修改失败"),
    DELETE_BATCH_FAIL("1005", "批量删除失败"),
    RANK_IS_NULL("1006", "存在下级功能不能删除"),
    IP_NOT_LEGITIMATE("1007", "输入的ip不合法"),
    SELECT_ONE_FAILED("1008", "查询的数据有多个，请更加详细描述"),
    EXCEL_NOT_EXPORT("1009", "选择的导出的数量较大，请缩小范围"),
    EXPORT_NOT_SUCCESS("1010", "Excel导出失败"),
    ACCOUNT_IS_NOT_EXITS("1011", "用户名已存在"),
    TIMEOUT_NOT_NULL("1012", "超时时间不能为空"),
    NET_WORK_NAME_EXITE("1013", "同一领域下，网络域名称不可重复"),
    IP_RANGE_NOT_TRUE("1014", "网络域范围不合法"),
    IP_RANGE_EXITS("1015", "网络域范围已存在"),
    IP_ALREADY_EXISTS("1016", "白名单中已包含相同IP地址"),
    TIME_OUT_LIMMIT("1017", "超时时间不在5-30分钟之内"),
    IP_RANGE_NOT_CONTAINS("1018", "以下IP不在父组范围之内"),
    IP_RANGE_CONTAINS("1019", "IP范围有冲突"),
    IP_RANGE_CONTAINS_SPECIAL("1020", "IP范围不合法"),
    THREAT_MANUAL_ISOLATION("1021", "手动隔离失败，请重试"),
    THREAT_MANUAL_ISOLATION_FAILURE("1022", "无效操作"),
    MAIL_SEND_FAILURE("1023", "邮件发送失败，请联系管理员"),
    BEFOR_DELEGE_ACCOUNT("1024", "请先删除用户"),
    SYNC_SYNC_MANAGEMENT("1025", "同步失败，请联系管理员"),
    IP_ADDRESS_EXIST("1026", "交换机或防火墙ip地址已存在"),
    IP_NULL_ERROR("1027", "请求的IP地址为空"),
    CAN_NOT_LOGIN("1028", "交换机或防火墙无法登陆，请确认账户密码"),
    CAN_NOT_PING("1029", "无法连接到当前IP，请检查IP是否输入正确"),
    REQ_TIMEOUT("1030", "请求超时"),
    RULE_NAME_REPEAT("1031", "规则名称不能重复"),
    RULE_REPEAT("1032", "已存在相同规则"),
    NOT_REPEAT_ISOLATION("1033", "请选择隔离范围"),
    SECURITYMANAGER_TOO_LONG("1034", "安全管理员用户设置过多"),
    DESKTOPSUPPORT_TOO_LONG("1035", "桌面管理员用户设置过多"),
    MINIMUM_CUMULATIVE_TIMES("1036", "累计次数的最小值不能等于小于0"),
    MAX_MUST_LTE_MIN("1037", "当前规则配置的威胁等级填写的范围需小于高级别的最小的累计次数"),
    MINIMUM_MAX_LTE_MIN("1038", "累计次数的最小值不能大于等于最大值"),
    MIN_MUST_LTE_MAX("1039", "当前规则配置的威胁等级填写的范围需大于低级别的最大的累计次数"),
    INTERVALTIME_NOT_IDENTICAL("1040", "相同规则类型，不同的威胁等级的时间范围要求保持一致"),
    RULE_INTERVALTIME_INCONSISTENCY("1041", "相同数据来源匹配的规则时间应该保持一致,是否继续进行修改"),
    RULE_UPDATE_MORE_FREQUENT("1042", "规则修改频繁，请休息两分钟"),
    THREAT_RECORD_ERROR("1043", "报表统计失败"),
    THE_ISOLATION_DEVICE_IS_EMPTY("1044", "隔离设备不能为空");

    public String message;
    private String code;

    private ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        for (ResultStatus item : ResultStatus.values()) {
            if (StringUtils.isNotBlank(code) && StringUtils.equals(item.getCode(), code)) {
                return item.getMessage();
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
