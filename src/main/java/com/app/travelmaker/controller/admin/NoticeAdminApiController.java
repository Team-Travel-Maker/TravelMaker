package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.cs.request.CustomServiceDTO;
import com.app.travelmaker.domain.notice.request.NoticeRequestDTO;
import com.app.travelmaker.domain.notice.response.NoticeResponseDTO;
import com.app.travelmaker.service.notice.NoticeService;
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
@RequestMapping("api/admins/notice")
public class NoticeAdminApiController {

    private final NoticeService noticeService;

    /*공지 등록*/
    @PostMapping("")
    public void write(@RequestPart(required = true, value = "noticeRequestDTO") NoticeRequestDTO noticeRequestDTO){
        noticeService.write(noticeRequestDTO);
    }

    @GetMapping("")
    public Page<NoticeResponseDTO> getList(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return noticeService.getListWithPage(pageable);
    }

    @PutMapping("")
    public void modify(@RequestPart(required = true, value = "noticeRequestDTO") NoticeRequestDTO noticeRequestDTO){
        noticeService.modifyNotice(noticeRequestDTO);
    }


    @GetMapping(path = {"detail/{id}", "modify/{id}"})
    public NoticeResponseDTO detail(@PathVariable Long id){
        return noticeService.detail(id);
    }

    @DeleteMapping("")
    public void delete(@RequestPart(required = true) List<Long> ids){
        noticeService.noticeDelete(ids);
    }

}
