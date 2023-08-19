package com.app.travelmaker.repository.member;

import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.mebmer.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberDSL {
    long updateMemberPoints(Long memberId, Integer giftCardTotalPrice);

    public Page<MemberResponseDTO> getList(Pageable pageable);

    public Integer memberCount(String memberEmail);

    public void modifyStatus(Long id);
    public void modifyType(Long id);
    public void modifyAdmin(Long id);
}
