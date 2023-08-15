package com.app.travelmaker.embeddable.alarm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
@ToString
public class Alarm {
    /**
     * emailBenefitEvent (알람이벤트 동의)
     * */
    @NotNull private boolean emailBenefitEvent;

    /**
     * emailSuggestion (알람 제안 동의)
     * */
    @NotNull private boolean emailSuggestion;

    /**
     * snsBenefitEvent (SNS 수신 동의)
     * */
    @NotNull private boolean snsBenefitEvent;
}
