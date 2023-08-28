package com.app.travelmaker.service.mypage.my.account;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.file.FileRepository;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.service.MemberSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyAccountServiceImpl extends AccountSupport implements MyAccountService, MemberSupport {
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void updateEmailBenefitEventAlarm(boolean settingValue) {
        Long memberId = authenticationInfo().getId();
        authenticationInfo().setEmailBenefitEvent(settingValue);
        long result = memberRepository.updateEmailBenefitEventAlarm(memberId, settingValue);
    }

    @Override
    public void updateEmailSuggestionAlarm(boolean settingValue) {
        Long memberId = authenticationInfo().getId();
        authenticationInfo().setEmailSuggestion(settingValue);
        long result = memberRepository.updateEmailSuggestionAlarm(memberId, settingValue);
    }

    @Override
    public void updateSnsBenefitEventAlarm(boolean settingValue) {
        Long memberId = authenticationInfo().getId();
        authenticationInfo().setSnsBenefitEvent(settingValue);
        long result = memberRepository.updateSnsBenefitEventAlarm(memberId, settingValue);
    }

    @Override
    public void withdrawal() {
        memberRepository.delete(toMemberEntity(authenticationInfo()));
    }

    @Override
    public void updateMemberName(String memberName) {
        authenticationInfo().setMemberName(memberName);
        memberRepository.updateMemberName(authenticationInfo().getId(), memberName);
    }

    @Override
    public void uploadProfile(FileDTO fileDTO) {
        // 기존에 프로필 파일이 있으면 삭제
        if(authenticationInfo().getFileId() != null) {
            fileRepository.deleteById(authenticationInfo().getFileId());
        }
        
        // 새로운 프로필 파일 등록, member에 fileid 등록
        File file = fileRepository.save(toEntity(fileDTO, toMemberEntity(authenticationInfo())));

        authenticationInfo().setFileId(file.getId());
        authenticationInfo().setFileName(file.getFileName());
        authenticationInfo().setFileSize(file.getFileSize());
        authenticationInfo().setFileUuid(file.getFileUuid());
        authenticationInfo().setFilePath(file.getFilePath());
        authenticationInfo().setFileType(file.getFileType());

        Member member = memberRepository.findById(authenticationInfo().getId()).get();
        member.setFile(file);
        memberRepository.save(member);
    }

    @Override
    public Boolean checkPassword(String oldPassword) {
        Member member = memberRepository.findById(authenticationInfo().getId()).get();

        return passwordEncoder.matches(oldPassword, member.getMemberPw());
    }

    @Override
    public void updatePassword(String newPassword) {
        memberRepository.resetPw(authenticationInfo().getId(), newPassword);
    }

    @Override
    public void updateMobile(String mobile) {
        authenticationInfo().setMemberPhone(mobile);
        memberRepository.updateMobile(authenticationInfo().getId(), mobile);
    }
}
