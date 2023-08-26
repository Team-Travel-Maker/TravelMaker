package com.app.travelmaker.controller.goWith;


import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.service.goWith.GoWithService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("goWith-list")
    @ResponseBody
//    public Slice<GoWithDTO> getList(@PageableDefault(page = 0, size = 10) Pageable pageable,
//                                    @RequestParam(name="region",required = false)GoWithRegionType region) {
//        final Slice<GoWithDTO> gowiths = goWithService.getListBySliceAndSorting(pageable, region);
//        log.info("===========================================================");
//        log.info("hasNext(): {}", gowiths.hasNext());
////        return gowiths;
//        if (region != null) {
//            return goWithService.getListBySliceAndSorting(pageable, region);
//        } else {
//            return goWithService.getListBySlice(pageable);
//        }
    public ResponseEntity<Slice<GoWithDTO>> getList(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(name = "region", required = false) GoWithRegionType region) {

        final Slice<GoWithDTO> gowiths = goWithService.getListBySliceAndSorting(pageable, region);
        log.info("===========================================================");
        log.info("hasNext(): {}", gowiths.hasNext());

        if (region != null) {
            return ResponseEntity.ok(goWithService.getListBySliceAndSorting(pageable, region));
        } else {
            return ResponseEntity.ok(goWithService.getListBySlice(pageable));
        }
    }

}
