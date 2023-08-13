package com.app.travelmaker.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommunityType {

    /**
     * REVIEW : 일반 후기
     * IMPROVEMENT : 개선 요청
     * COMMUNICATION : 소통
     * */
    REVIEW("REVIEW", "일반 후기"),
    IMPROVEMENT("IMPROVEMENT", "개선 요청"),
    COMMUNICATION("COMMUNICATION", "소통");

    private final String code;
    private final String name;

}
