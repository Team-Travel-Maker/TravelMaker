package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
@RequestMapping("api/admins/member")
public class MemberAdminApiController {

    private final MemberService memberService;

    @GetMapping("")
    public Page<MemberResponseDTO> getList(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return memberService.getList(pageable);
    }

    @PutMapping("status")
    public void modifyStatus(@RequestPart List<Long> statusIds){
        memberService.modifyStatus(statusIds);
    }

    @PutMapping("type")
    public void modifyType(@RequestPart List<Long> typeIds){
        memberService.modifyType(typeIds);
    }

    @PutMapping("admin")
    public void modifyAdmin(@RequestPart List<Long> adminIds){ memberService.modifyAdmin(adminIds);}
}
