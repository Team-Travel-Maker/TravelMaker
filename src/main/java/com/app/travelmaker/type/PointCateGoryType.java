package com.app.travelmaker.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PointCateGoryType {
    /**
     * USE : USE(사용)
     * EARN : EARN(적립)
     * */
    COMPANY("USE", "사용"),
    GENERAL("EARN", "적립");

    private final String code;
    private final String name;
}
