package com.app.travelmaker.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  MemberJoinAccountType {
    /**
     * KAKAO : 카카오
     * NAVER : 네이버
     * GENERAL : 일반
     * */
    KAKAO("KAKAO", "카카오"),
    NAVER("NAVER", "네이버"),
    GENERAL("GENERAL", "일반회원"),
    GOOGLE("GOOGLE", "구글");

    private final String code;
    private final String name;


}
