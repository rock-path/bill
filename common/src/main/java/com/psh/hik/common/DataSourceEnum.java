package com.psh.hik.common;

public enum DataSourceEnum {

    DB1("db1"), DB2("db2");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}