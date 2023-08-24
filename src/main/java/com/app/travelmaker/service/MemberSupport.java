package com.app.travelmaker.service;

import com.app.travelmaker.domain.cs.request.CustomServiceDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.embeddable.alarm.Alarm;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.mebmer.Member;

public interface MemberSupport {

    public default Member toMemberEntity(MemberResponseDTO memberResponseDTO){

        Alarm alarm = new Alarm();
        Address address =new Address();
        alarm.setEmailBenefitEvent(memberResponseDTO.isEmailBenefitEvent());
        alarm.setEmailSuggestion(memberResponseDTO.isEmailSuggestion());
        alarm.setSnsBenefitEvent(memberResponseDTO.isSnsBenefitEvent());
        address.setAddress(memberResponseDTO.getAddress());
        address.setAddressDetail(memberResponseDTO.getAddressDetail());
        address.setPostcode(memberResponseDTO.getPostCode());

        return Member.builder()
                .id(memberResponseDTO.getId())
                .address(address)
                .alarm(alarm)
                .memberEmail(memberResponseDTO.getMemberEmail())
                .memberInterestRegion(memberResponseDTO.getMemberInterestRegion())
                .memberEcoPoint(memberResponseDTO.getMemberEcoPoint())
                .memberJoinAccountType(memberResponseDTO.getMemberJoinAccountType())
                .memberPhone(memberResponseDTO.getMemberPhone())
                .memberName(memberResponseDTO.getMemberName())
                .memberRole(memberResponseDTO.getMemberRole())
                .createdDate(memberResponseDTO.getCreatedDate())
                .updatedDate(memberResponseDTO.getUpdatedDate())
                .build();

    }
}
