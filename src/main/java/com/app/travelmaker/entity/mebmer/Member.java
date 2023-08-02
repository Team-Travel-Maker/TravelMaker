package com.app.travelmaker.entity.mebmer;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.embeddable.alarm.Alarm;
import com.app.travelmaker.type.FileType;
import com.app.travelmaker.type.MemberJoinAccountType;
import com.app.travelmaker.type.MemberType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Member Entity (회원)
 * */

@Entity
@Table(name = "TBL_MEMBER")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_MEMBER SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Member extends Period {

    /**
     * Member PK(고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Member Email(회원 이메일)
     * */
    @NotNull @Column(unique = true)
    private String memberEmail;

    /**
     * Member PW(회원 비밀번호)
     * */
    @NotNull private String memberPw;

    /**
     * Member Name(회원 이름)
     * */
    @NotNull private String memberName;

    /**
     * Member ALARM (알람 설정 여부)
     * */
    @Embedded @NotNull private Alarm alarm;

    /**
     * Member Address(회원 주소)
     * */
    @Embedded @NotNull private Address address;

    /**
     * Member PHONE(회원 전화 번호)
     * */
    @NotNull private String memberPhone;

    /**
     * Member TYPE(회원)
     * (C : COMPANY 업체 회원)
     * (G : GENERAL 일반 회원)
     * (A : ADMIN 관리자)
     * */
    @Enumerated(EnumType.STRING)
    @NotNull private MemberType memberType;

    /**
     * Member ECO POINT(회원 에코 포인트)
     * */
    @NotNull private Integer memberEcoPoint;

    /**
     * Member JOIN ACCOUNT(SNS 로그인 여부)
     *  KAKAO : 카카오
     *  NAVER : 네이버
     *  GENERAL : 일반
     * */
    @Enumerated(EnumType.STRING)
    @NotNull private MemberJoinAccountType memberJoinAccountType;

    /**
     * Member INTEREST REGION(회원 흥미 지역)
     * */
    @NotNull private String memberInterestRegion;

    /**
     *  Member File 부분
     *
     *   FileType
     *   REPRESENTATIVE : 대표 이미지
     *   NON_REPRESENTATIVE : 제목 이미지
     *   CONTENT_REPRESENTATIVE : 내용 이미지
     *
     * */
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileType fileType;
    private Long fileSize;

    /**
     * Member SNS PROFILE (SNS 계정일 경우 프로필 사진 링크)
     * */
    private String snsProfile;

    /**
     * Member Status (회원 탈퇴 상태)
     * */
    private boolean deleted = Boolean.FALSE;












}
