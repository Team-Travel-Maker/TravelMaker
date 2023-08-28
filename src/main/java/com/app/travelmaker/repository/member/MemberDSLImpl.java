package com.app.travelmaker.repository.member;

import com.app.travelmaker.constant.Role;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.domain.member.response.MemberJoinResponseDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.file.File;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.app.travelmaker.entity.mebmer.QMember.*;

public class MemberDSLImpl implements MemberDSL {
    @Autowired
    private JPAQueryFactory query;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<MemberJoinResponseDTO> memberCheckForOauthAndLogin(String memberEmail) {
        MemberJoinResponseDTO memberJoinResponseDTO = query.select(Projections.fields(MemberJoinResponseDTO.class,
                member.memberEmail,
                member.memberJoinAccountType
        )).from(member)
                .where(member.memberEmail.eq(memberEmail)).fetchOne();
        return Optional.ofNullable(memberJoinResponseDTO);
    }

    @Override
    public long updateMemberPoints(Long memberId, Integer giftCardTotalPrice) {
        return query.update(member)
                .set(member.memberEcoPoint, member.memberEcoPoint.subtract(giftCardTotalPrice))
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }

    @Override
    public long updateEmailBenefitEventAlarm(Long memberId, boolean settingValue) {
        return query.update(member)
                .set(member.alarm.emailBenefitEvent, settingValue)
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }

    @Override
    public long updateEmailSuggestionAlarm(Long memberId, boolean settingValue) {
        return query.update(member)
                .set(member.alarm.emailSuggestion, settingValue)
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }

    @Override
    public long updateSnsBenefitEventAlarm(Long memberId, boolean settingValue) {
        return query.update(member)
                .set(member.alarm.snsBenefitEvent, settingValue)
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }

    @Override
    public Page<MemberResponseDTO> getList(Pageable pageable) {
        List<MemberResponseDTO> members = query.select(Projections.fields(MemberResponseDTO.class,
                member.id,
                member.memberEmail,
                member.memberName,
                member.alarm.emailBenefitEvent.as("emailBenefitEvent"),
                member.alarm.emailSuggestion.as("emailSuggestion"),
                member.alarm.snsBenefitEvent.as("snsBenefitEvent"),
                member.address.address.as("address"),
                member.address.addressDetail.as("addressDetail"),
                member.address.postcode.as("postCode"),
                member.memberPhone,
                member.memberRole,
                member.memberEcoPoint,
                member.memberJoinAccountType,
                member.memberInterestRegion,
                member.createdDate,
                member.updatedDate
                 ))
                .from(member)
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count()).from(member).fetchOne();

        return new PageImpl<>(members, pageable, count);

    }

    @Override
    public void modifyStatus(Long id) {
        Boolean deleted = query.select(member.deleted).from(member).where(member.id.eq(id)).fetchOne();

        if(deleted){
            query.update(member)
                    .set(member.deleted,false)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }else{
            query.update(member)
                    .set(member.deleted,true)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }

    }

    @Override
    public void modifyType(Long id) {

        Role foundRole = query.select(member.memberRole).from(member).where(member.id.eq(id)).fetchOne();

        if(foundRole.getCode().equals("GENERAL")){
            query.update(member)
                    .set(member.memberRole, Role.COMPANY)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }else if(foundRole.getCode().equals("COMPANY")){
            query.update(member)
                    .set(member.memberRole, Role.GENERAL)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }
    }

    @Override
    public void modifyAdmin(Long id) {

        Role foundRole = query.select(member.memberRole).from(member).where(member.id.eq(id)).fetchOne();

        if(foundRole.getCode().equals("ADMIN")){
            query.update(member)
                    .set(member.memberRole, Role.GENERAL)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }else if(foundRole.getCode().equals("COMPANY") || foundRole.getCode().equals("GENERAL")){
            query.update(member)
                    .set(member.memberRole, Role.ADMIN)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }
    }

    @Override
    public void resetPw(Long id, String newPassword) {
        query.update(member)
                .set(member.memberPw, passwordEncoder.encode(newPassword))
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(id))
                .execute();
    }

    @Override
    public void oauthJoin(MemberRequestDTO memberRequestDTO) {

        query.update(member)
                .set(member.address.address, memberRequestDTO.getAddress())
                .set(member.address.addressDetail, memberRequestDTO.getAddressDetail())
                .set(member.address.postcode, memberRequestDTO.getPostCode())
                .set(member.alarm.emailBenefitEvent, memberRequestDTO.isEmailSuggestion())
                .set(member.alarm.emailSuggestion, memberRequestDTO.isEmailBenefitEvent())
                .set(member.alarm.snsBenefitEvent, memberRequestDTO.isSnsBenefitEvent())
                .set(member.memberRole, memberRequestDTO.getMemberRole())
                .set(member.memberName, memberRequestDTO.getMemberName())
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.memberEmail.eq(memberRequestDTO.getMemberEmail()))
                .execute();
        
        /** 카카오면 폰번호 업데이트*/
        if(memberRequestDTO.getMemberPhone() !=null){
                    query.update(member)
                    .set(member.memberPhone, memberRequestDTO.getMemberPhone())
                    .where(member.memberEmail.eq(memberRequestDTO.getMemberEmail()))
                    .execute();
        }
    }

    @Override
    public List<MemberJoinResponseDTO> findMemberEmailByMemberPhone(String memberPhoneNumber) {
        return query.select(Projections.fields(MemberJoinResponseDTO.class,
                member.memberEmail,
                member.memberJoinAccountType))
                .from(member).where(member.memberPhone.eq(memberPhoneNumber)).fetch();
    }

    @Override
    public Optional<Long> findIdByMemberEmail(String memberEmail) {
       return Optional.ofNullable(query.select(member.id).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

    @Override
    public void updateMemberName(Long memberId, String memberName) {
        query.update(member)
                .set(member.memberName, memberName)
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }

    @Override
    public void updateMobile(Long memberId, String mobile) {
        query.update(member)
                .set(member.memberPhone, mobile)
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }

    @Override
    public void setInterestsRegion(Long memberId, String interestsStr) {
        query.update(member)
                .set(member.memberInterestRegion, interestsStr)
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }
}
