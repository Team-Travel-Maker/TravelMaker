package com.app.travelmaker.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAILED("AUTH01", "LOGIN_FAILED", 401),
    AUTHENTICATION_FAILED("AUTH02", "AUTHENTICATION_FAILED", 401);

    private final String code;
    private final String message;
    private int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
