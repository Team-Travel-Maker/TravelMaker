package com.app.travelmaker.domain.member.request;

import com.app.travelmaker.constant.MemberJoinAccountType;
import com.app.travelmaker.constant.Role;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.embeddable.alarm.Alarm;
import com.app.travelmaker.entity.file.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@Data
@NoArgsConstructor
public class MemberRequestDTO {

    private String memberEmail;
    private String memberPw;
    private String memberName;
    private boolean emailBenefitEvent;
    private boolean emailSuggestion;
    private boolean snsBenefitEvent;
    private String address;
    private String addressDetail;
    private String postCode;
    private String memberPhone;
    private Role memberRole;
    private MemberJoinAccountType memberJoinAccountType;
    private String memberInterestRegion;

}
