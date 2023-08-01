package com.app.travelmaker.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FileType {
    /**
     * REPRESENTATIVE : 대표 이미지
     * NON_REPRESENTATIVE : 제목 이미지
     * CONTENT_REPRESENTATIVE : 내용 이미지
     * */
    REPRESENTATIVE("REPRESENTATIVE", "대표 이미지"),
    NON_REPRESENTATIVE("NON_REPRESENTATIVE", "제목 이미지"),
    CONTENT_REPRESENTATIVE("CONTENT_REPRESENTATIVE", "내용 이미지");

    private final String code;
    private final String name;

    FileType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
