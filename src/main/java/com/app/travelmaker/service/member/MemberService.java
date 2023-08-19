package com.app.travelmaker.service.member;

import com.app.travelmaker.constant.MemberJoinAccountType;
import com.app.travelmaker.constant.Role;
import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.embeddable.alarm.Alarm;
import com.app.travelmaker.entity.mebmer.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface MemberService extends UserDetailsService {

    public void join(MemberRequestDTO memberRequestDTO, PasswordEncoder passwordEncoder);

    public boolean checkId(String memberEmail);

    public Page<MemberResponseDTO> getList(Pageable pageable);

    public void modifyStatus(List<Long> statusIds);
    public void modifyType(List<Long> typeIds);
    public void modifyAdmin(List<Long> adminIds);


    public String certifiedPhoneNumber(String to);

    default Member toEntity(MemberRequestDTO memberRequestDTO){
        Alarm alarm = new Alarm();
        Address address = new Address();

        alarm.setEmailBenefitEvent(memberRequestDTO.isEmailBenefitEvent());
        alarm.setEmailSuggestion(memberRequestDTO.isEmailSuggestion());
        alarm.setSnsBenefitEvent(memberRequestDTO.isSnsBenefitEvent());

        address.setPostcode(memberRequestDTO.getPostCode());
        address.setAddressDetail(memberRequestDTO.getAddressDetail());
        address.setAddress(memberRequestDTO.getAddress());

        return  Member.builder()
                .memberName(memberRequestDTO.getMemberName())
                .alarm(alarm)
                .address(address)
                .memberPhone(memberRequestDTO.getMemberPhone())
                .memberJoinAccountType(MemberJoinAccountType.GENERAL)
                .memberPw(memberRequestDTO.getMemberPw())
                .memberEmail(memberRequestDTO.getMemberEmail())
                .memberRole(memberRequestDTO.getMemberRole())
                .build();
    };
}
