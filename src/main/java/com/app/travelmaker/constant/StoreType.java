package com.app.travelmaker.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  StoreType {
    /**
     * RESTAURANT : 음식점
     * LODGMENT :숙박
     * */
    RESTAURANT("RESTAURANT", "음식점"),
    LODGMENT("LODGMENT", "숙박");

    private final String code;
    private final String name;

}
