package com.app.travelmaker.repository.member;

import com.app.travelmaker.entity.mebmer.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberDSL {

    public Optional<Member> findByMemberEmail(String memberEmail);

    //    아이디로 전체 정보 조회

}
