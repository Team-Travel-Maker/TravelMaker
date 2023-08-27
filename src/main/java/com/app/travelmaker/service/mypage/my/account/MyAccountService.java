package com.app.travelmaker.service.mypage.my.account;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.mebmer.Member;

public interface MyAccountService {
    void updateEmailBenefitEventAlarm(boolean settingValue);

    void updateEmailSuggestionAlarm(boolean settingValue);

    void updateSnsBenefitEventAlarm(boolean settingValue);

    void withdrawal();

    void updateMemberName(String memberName);

    void uploadProfile(FileDTO fileDTO);


    default File toEntity(FileDTO fileDTO, Member member){
        return File.builder().id(fileDTO.getId())
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileUuid(fileDTO.getFileUuid())
                .fileSize(fileDTO.getFileSize())
                .member(member)
                .build();
    }

    Boolean checkPassword(String oldPassword);

    void updatePassword(String newPassword);

    void updateMobile(String mobile);
}
