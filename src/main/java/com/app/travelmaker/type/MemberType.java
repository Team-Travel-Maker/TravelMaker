package com.app.travelmaker.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  MemberType {
    /**
     * COMPANY : COMPANY(업체 회원)
     * GENERAL : GENERAL(일반 회원)
     * ADMIN : ADMIN(관리자)
     * */
    COMPANY("COMPANY", "업체"),
    GENERAL("GENERAL", "일반"),
    ADMIN("ADMIN", "관리자");

    private final String code;
    private final String name;

    MemberType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
