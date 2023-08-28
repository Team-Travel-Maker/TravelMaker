package com.app.travelmaker.controller.goWith;


import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.service.goWith.GoWithService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/goWith/*")
public class GoWithApiController {
    private final MemberRepository memberRepository;
    private final GoWithService goWithService;

//    @GetMapping("list")
//    public void goToList(@AuthenticationPrincipal MemberDetail memberDetail, HttpSession session){
//        log.info("======{}", memberDetail.toString());
//        if(memberDetail != null){ //일반 로그인
//            authen.setAttribute("member",new MemberDTO(memberRepository.findByMemberId(memberDetail.getMemberId)).get());
//        }else { //OAuth 로그인
//
//        }
    //작성
//    @PostMapping("goWith-registration")

    @GetMapping("goWith-list")
    public Page<GoWithDTO> getList(@PageableDefault(size = 10, page = 0) Pageable pageable, GoWithRegionType regionType){
    return goWithService.getList(pageable,regionType);
}

    //    게시글 조회
    @GetMapping("goWith-detail/{id}")
    public GoWithDTO getPost(@PathVariable Long id) {
        return goWithService.getGoWith(id);
    }

    //게시글 수정
    // @PutMapping("modify")
    //    public void changeStatus(@RequestPart(value = "result") StoreResponseDTO result){
    //        log.info(result.toString());
    //        storeAdminService.modifyStatus(result);
    //    }
    //게시글 삭제
    //    @DeleteMapping("")
    //    public void delete(@RequestPart List<Long> ids){
    //        storeAdminService.deleteStore(ids);
    //    }
    //}

}
