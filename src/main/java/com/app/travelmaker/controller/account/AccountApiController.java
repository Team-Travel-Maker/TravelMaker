package com.app.travelmaker.controller.account;

import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("api/accounts/*")
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
public class AccountApiController {

    private final MemberService memberService;

    /*아이디 중복 체크*/
    @PostMapping("check")
    public boolean checkId(String memberEmail){
        return memberService.checkId(memberEmail);
    }

    /*일반 회원가입*/
    @PostMapping("join")
    public void join(MemberRequestDTO memberRequestDTO){
        log.info(memberRequestDTO.toString());
        memberService.join(memberRequestDTO);
    }


    @GetMapping("/check/sendSMS")
    public String sendSMS(String to) {
        return memberService.certifiedPhoneNumber(to);
    }

}
