package com.app.travelmaker.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum JoinCheckType {


    SNS("SNS", "SNS 계정"),
    TRUE("TRUE", "아이디 없음"),
    FALSE("FALSE", "아이디 있음");

    private final String code;
    private final String name;
}
