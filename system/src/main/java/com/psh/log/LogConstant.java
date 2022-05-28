package com.psh.log;


import com.psh.entity.BillIncome;

public class LogConstant {

    public static String API = "/api";

    public static String CH2 = ",";

    public static String CH1 = "0";

    public static String DELETED = "deleted";

    public static String LOGS = "Log_resource";


    public static final String action1 = "查询";

    public static final String action2 = "增加";

    public static final String action3 = "删除";

    public static final String action4 = "修改";

    public static String SYNC1 = "URL";

    public static String SYNC2 = "logResource";

    public static void main(String[] args) {
        BillIncome bu = new BillIncome();
        bu.setDescd("111");
        System.out.println(bu);


    }

    public static void test(Boolean boo, String s, StringBuilder sb, Long l1) {

    }


}
