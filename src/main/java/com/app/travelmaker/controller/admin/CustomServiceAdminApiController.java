package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.cs.request.CsAnswerDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.entity.cs.CsAnswer;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.repository.cs.CsAnswerRepository;
import com.app.travelmaker.repository.cs.CustomServiceFileRepository;
import com.app.travelmaker.repository.cs.CustomServiceRepository;
import com.app.travelmaker.repository.file.FileRepository;
import com.app.travelmaker.service.cs.CustomSerivceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
@RequestMapping("api/admins/*")
public class CustomServiceAdminApiController {

    private final CustomSerivceService customSerivceService;
    private final CsAnswerRepository csAnswerRepository;
    private final CustomServiceRepository customServiceRepository;

    /*문의 목록*/
    @GetMapping("inquiry/list")
    public Page<CustomServiceResponseDTO> getList(@PageableDefault(page = 0, size = 10) Pageable pageable,@RequestParam(value = "keyword", required = false) String keyword){
        log.info(keyword);
        return  customSerivceService.getList(pageable,keyword);
    }

    /*문의 상세*/
    @GetMapping(path = {"inquiry/detail/{id}", "inquiry/modify/{id}"})
    public CustomServiceResponseDTO detail(@PathVariable Long id){
        return customSerivceService.detail(id);
    }

    /*문의삭제*/
    @DeleteMapping("inquiry")
    public void inquiryDelete(@RequestPart(value = "ids") List<Long> ids){
        customSerivceService.inquiryDelete(ids);
    }

    /*답변 등록*/
    @PostMapping("answer/{id}")
    public void answerRegister(@PathVariable(required = true) Long id,@RequestPart(value = "csAnswerDTO") CsAnswerDTO csAnswerDTO){
        log.info("들어옴");
        Optional<CustomService> foundService = customServiceRepository.findById(id);
        CustomService customService = foundService.orElseThrow(() -> {
            throw new RuntimeException();
        });
        csAnswerDTO.setCustomService(customService);

        customSerivceService.answerRegister(csAnswerDTO);

    }


    /*답변 수정*/
    @PutMapping("answer/{id}")
    public void answerModify(@PathVariable(required = true) Long id,@RequestPart(value = "csAnswerDTO") CsAnswerDTO csAnswerDTO){
        Optional<CustomService> foundService = customServiceRepository.findById(id);
        Optional<CsAnswer> foundAnswer = csAnswerRepository.findById(csAnswerDTO.getId());

        CsAnswer csAnswer = foundAnswer.orElseThrow(() -> {
            throw new RuntimeException();
        });
        CustomService customService = foundService.orElseThrow(() -> {
            throw new RuntimeException();
        });
        csAnswerDTO.setCustomService(customService);
        csAnswerDTO.setCreatedDate(csAnswer.getCreatedDate());
        customSerivceService.answerModify(csAnswerDTO);
    }

}
