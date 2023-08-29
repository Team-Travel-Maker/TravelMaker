package com.app.travelmaker.constant;

import lombok.Getter;
import org.apache.http.HttpStatus;

@Getter
public enum ErrorCode {
    LOGIN_FAILED("AUTH01", "LOGIN_FAILED", 401),
    AUTHENTICATION_FAILED("AUTH02", "AUTHENTICATION_FAILED", 401),
    OAUTH_FAILED("OAUTH_FAILED", "이미 일반 가입된 이메일입니다.", HttpStatus.SC_BAD_REQUEST);

    private final String code;
    private final String message;
    private int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
