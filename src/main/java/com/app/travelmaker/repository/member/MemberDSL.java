package com.app.travelmaker.repository.member;

import com.app.travelmaker.entity.mebmer.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberDSL {
    long updateMemberPoints(Long memberId, Integer giftCardTotalPrice);

    public Page<Member> getList(Pageable pageable);

    public void modifyStatus(Long id);
    public void modifyType(Long id);
}
