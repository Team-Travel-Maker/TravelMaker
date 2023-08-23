package com.app.travelmaker.controller.account;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/accounts/*")
public class AccountController extends AccountSupport {

    private final MemberRepository memberRepository;

    //로그인
    @GetMapping("login/login")
    public void goToLogin(@RequestParam(value = "error", required = false) boolean error,
                          @RequestParam(value = "exception", required = false) String exception,
                          Model model){
                model.addAttribute("error", error);
                model.addAttribute("exception", exception);
        ;}

    @PostMapping("login/login")
    public String processUsername(String memberEmail, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        return "redirect:/accounts/password/input"; // 비밀번호 입력 페이지로 리다이렉트
    }


    @GetMapping("join/join")
    public void goToJoinForm(HttpSession session, Model model){
        model.addAttribute("oauthMember", authenticationInfo().getMemberEmail());
        session.invalidate();
        ;}

    //회원 가입
    @PostMapping("join/join")
    public void goToJoin(String memberEmail, Model model){
        model.addAttribute("memberEmail", memberEmail);
    }

    //계정 찾기
    @GetMapping("account/find")
    public void goToFind(){;}

    //계정 찾기 성공
    @GetMapping("account/found")
    public void goToFound(){;}
    
    //이미 존재하는 계정 확인
    @GetMapping("account/exiting")
    public void goToExiting(){;}

    //계정이 존재 하지 않을 때
    @GetMapping("account/not-exiting")
    public void goToNotExiting(){;}

    //비밀번호 입력
    @GetMapping("password/input")
    public void goToPassword(){;}



    //비밀번호 재설정 이메일 전송 확인 화면
    @GetMapping("password/email/{id}")
    public String goToPasswordEmail(@PathVariable Long id, Model model){
        AtomicReference<String> email= new AtomicReference<>("");
        memberRepository.findById(id).ifPresent(member ->
                email.set(member.getMemberEmail()));
        model.addAttribute("id", email.get());
        return "/accounts/password/email";
    }



    //비밀번호 재설정 화면
    @GetMapping("ok/reset")
    public void goToPasswordReset(@RequestParam(required = true) Long id, Model model){
        model.addAttribute("id", id);
        }

    //비밀번호 재설정 성공 화면
    @GetMapping("ok/reset-ok")
    public void goToPasswordResetOk(HttpSession session){
        session.invalidate();
    }
}
