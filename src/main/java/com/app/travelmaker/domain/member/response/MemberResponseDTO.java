package com.app.travelmaker.domain.member.response;

import com.app.travelmaker.constant.FileType;
import com.app.travelmaker.constant.MemberJoinAccountType;
import com.app.travelmaker.constant.Role;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MemberResponseDTO {

    private Long id;
    private String memberEmail;
    private String memberName;
    private boolean emailBenefitEvent;
    private boolean emailSuggestion;
    private boolean snsBenefitEvent;
    private String address;
    private String addressDetail;
    private String postCode;
    private String memberPhone;
    private Role memberRole;
    private Integer memberEcoPoint;
    private MemberJoinAccountType memberJoinAccountType;
    private String memberInterestRegion;
    private String snsProFile;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long fileId;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileType fileType;
    private Long fileSize;


    public MemberResponseDTO(Member member){
        this.id = member.getId();
        this.memberEmail = member.getMemberEmail();
        this.memberName = member.getMemberName();
        this.emailBenefitEvent = member.getAlarm().isEmailBenefitEvent();
        this.emailSuggestion = member.getAlarm().isEmailSuggestion();
        this.snsBenefitEvent = member.getAlarm().isEmailSuggestion();
        this.address = member.getAddress().getAddress();
        this.addressDetail = member.getAddress().getAddressDetail();
        this.postCode = member.getAddress().getPostcode();
        this.memberPhone = member.getMemberPhone();
        this.memberRole = member.getMemberRole();
        this.memberEcoPoint = member.getMemberEcoPoint();
        this.memberJoinAccountType = member.getMemberJoinAccountType();
        this.memberInterestRegion = member.getMemberInterestRegion();
        this.snsProFile = member.getSnsProfile();
        this.createdDate = member.getCreatedDate();
        this.updatedDate = member.getUpdatedDate();
        if(member.getFile() != null){
            this.fileId = member.getFile().getId();
            this.fileName = member.getFile().getFileName();
            this.fileUuid = member.getFile().getFileUuid();
            this.filePath = member.getFile().getFilePath();
            this.fileType = member.getFile().getFileType();
            this.fileSize = member.getFile().getFileSize();
        }
    }
}
