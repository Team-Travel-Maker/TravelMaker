package com.app.travelmaker.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Role {


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

    private static final String ROLE_PREFIX = "ROLE_";

    public String getSecurityRole(){
        return ROLE_PREFIX + getCode();
    }


}
