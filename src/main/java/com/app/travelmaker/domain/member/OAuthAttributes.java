package com.app.travelmaker.domain.member;

import com.app.travelmaker.constant.MemberJoinAccountType;
import com.app.travelmaker.constant.Role;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.embeddable.alarm.Alarm;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@Slf4j
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String snsProfile;
    private final String mobile;
    private final MemberJoinAccountType memberJoinAccountType;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
//        userNameAttributeName은 .yml에 등록되어 있는 user-name-attribute 값이다.
        log.info("========================{}", userNameAttributeName);

//        registrationId는 네이버 로그인일 경우 naver이고, 카카오 로그인일 경우 kakao이다.
        log.info("========================{}", registrationId);
        if("naver".equals(registrationId)){
            return ofNaver(userNameAttributeName, attributes);
        }
        return ofKaKao(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKaKao(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get(userNameAttributeName);
        log.info("***************************");
        log.info(kakaoAccount.toString());
        return OAuthAttributes.builder()
                .email((String)kakaoAccount.get("email"))
                .name((String)((Map)kakaoAccount.get("profile")).get("nickname"))
                .snsProfile((String)((Map)kakaoAccount.get("profile")).get("thumbnail_image_url"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .memberJoinAccountType(MemberJoinAccountType.KAKAO)
                .build();

    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> naverAccount = (Map<String, Object>)attributes.get(userNameAttributeName);
        log.info("***************************");
        log.info(naverAccount.toString());
        return OAuthAttributes.builder()
                .email((String)naverAccount.get("email"))
                .name((String)naverAccount.get("name"))
                .mobile((String)naverAccount.get("mobile"))
                .snsProfile((String)naverAccount.get("profile_image"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .memberJoinAccountType(MemberJoinAccountType.NAVER)
                .build();

    }

    public Member toEntity(){
        Alarm alarm = new Alarm();
        Address address = new Address();

        alarm.setSnsBenefitEvent(Boolean.FALSE);
        alarm.setEmailBenefitEvent(Boolean.FALSE);
        alarm.setEmailSuggestion(Boolean.FALSE);

        address.setAddress("sns계정 수정될 예정");
        address.setAddressDetail("sns계정 수정될 예정");
        address.setPostcode("sns계정 수정될 예정");


        return Member.builder()
                .memberName(name)
                .memberEmail(email)
                .snsProfile(snsProfile)
                .memberRole(Role.WAIT)
                .memberPhone(mobile == null ? "카카오" : mobile)
                .memberJoinAccountType(memberJoinAccountType)
                .alarm(alarm)
                .address(address)
                .build();
    }
}
