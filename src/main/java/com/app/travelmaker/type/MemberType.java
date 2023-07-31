package com.app.travelmaker.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  MemberType {
    /**
     * C : COMPANY(업체 회원)
     * G : GENERAL(일반 회원)
     * A : ADMIN(관리자)
     * */
    COMPANY("C", "업체"),
    GENERAL("G", "일반"),
    ADMIN("A", "관리자");

    private final String code;
    private final String name;

}
