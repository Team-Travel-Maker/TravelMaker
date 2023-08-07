package com.app.travelmaker.repository.member;

import com.app.travelmaker.entity.mebmer.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
