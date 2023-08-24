package com.app.travelmaker.repository.member;

import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.domain.member.response.MemberJoinResponseDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberDSL {
    long updateMemberPoints(Long memberId, Integer giftCardTotalPrice);

    public Page<MemberResponseDTO> getList(Pageable pageable);

    public Optional<MemberJoinResponseDTO> memberCheckForOauthAndLogin(String memberEmail);

    public void oauthJoin(MemberRequestDTO memberRequestDTO);

    public void modifyStatus(Long id);
    public void modifyType(Long id);
    public void modifyAdmin(Long id);

    public void resetPw(Long id, String newPassword);

    public List<MemberJoinResponseDTO> findMemberEmailByMemberPhone(String memberPhoneNumber);

    public Optional<Long> findIdByMemberEmail(String memberEmail);
}
