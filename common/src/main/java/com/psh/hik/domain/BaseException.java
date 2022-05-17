package com.psh.hik.domain;

public class BaseException extends RuntimeException {

    private String errorCode;
    private String errorMessage;


    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}