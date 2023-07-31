package com.app.travelmaker.entity.address;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
@Getter
@Setter
public class Address {
    /**
     * address (주소)
     * */
    @NotNull private String address;

    /**
     * addressDetail (상세 주소)
     * */
    @NotNull private String addressDetail;

    /**
     * addressDetail (우편 번호)
     * */
    @NotNull private String postcode;
}
