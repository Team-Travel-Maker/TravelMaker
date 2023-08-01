package com.app.travelmaker.embeddable.alarm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
public class Alarm {
    /**
     * emailBenefitEvent (알람이벤트 동의)
     * */
    @NotNull private boolean emailBenefitEvent= Boolean.FALSE;

    /**
     * emailSuggestion (알람 제안 동의)
     * */
    @NotNull private boolean emailSuggestion = Boolean.FALSE;

    /**
     * snsBenefitEvent (SNS 수신 동의)
     * */
    @NotNull private boolean snsBenefitEvent= Boolean.FALSE;
}
