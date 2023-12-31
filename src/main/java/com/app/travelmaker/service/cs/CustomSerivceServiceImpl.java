package com.app.travelmaker.service.cs;

import com.app.travelmaker.domain.cs.request.CsAnswerDTO;
import com.app.travelmaker.domain.cs.request.CustomServiceDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.cs.CustomServiceFile;
import com.app.travelmaker.repository.cs.CsAnswerRepository;
import com.app.travelmaker.repository.cs.CustomServiceFileRepository;
import com.app.travelmaker.repository.cs.CustomServiceRepository;
import com.app.travelmaker.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor =Exception.class)
public class CustomSerivceServiceImpl implements CustomSerivceService {

    private final CustomServiceRepository customServiceRepository;
    private final CsAnswerRepository csAnswerRepository;
    private final CustomServiceFileRepository customServiceFileRepository;
    private final MemberRepository memberRepository;

    private final HttpSession session;



    @Override
    public Page<CustomServiceResponseDTO> getList(Pageable pageable, String keyword) {
        return customServiceRepository.getListWithPage(pageable,keyword);
    }

    @Override
    public void answerRegister(CsAnswerDTO csAnswerDTO) {
        csAnswerRepository.save(toEntity(csAnswerDTO));
    }

    @Override
    public void answerModify(CsAnswerDTO csAnswerDTO) {
        csAnswerRepository.save(toEntity(csAnswerDTO));
    }

    @Override
    public void register(CustomServiceDTO customServiceDTO) {

        Long id = customServiceRepository.save(toEntity(customServiceDTO)).getId();

        if(customServiceDTO.getFiles().size() >0){
            for (int i = 0; i < customServiceDTO.getFiles().size(); i++) {

                CustomService foundCustomService = customServiceRepository.findById(id).orElseThrow(() -> {
                    throw new RuntimeException();
                });
                customServiceFileRepository.save(CustomServiceFile.builder().customService(foundCustomService)
                        .fileName(customServiceDTO.getFiles().get(i).getFileName())
                        .fileSize(customServiceDTO.getFiles().get(i).getFileSize())
                        .fileType(customServiceDTO.getFiles().get(i).getFileType())
                        .fileUuid(customServiceDTO.getFiles().get(i).getFileUuid())
                        .filePath(customServiceDTO.getFiles().get(i).getFilePath())
                        .build());
            }
        }

    }

    /*문의 삭제*/
    @Override
    public void inquiryDelete(List<Long> ids) {
        /*문의 삭제하면 안에 답변 파일 삭제 상태로 변경*/
        ids.stream().forEach(id -> {
            customServiceRepository.findById(id)
                    .ifPresent(customService ->{
                        customService.getCsAnswers().forEach(answer-> csAnswerRepository.delete(answer));
                        customService.getCustomServiceFile().forEach(file -> customServiceFileRepository.deleteById(file.getId()));
                    });
             customServiceRepository.deleteById(id);
        });
    }

    @Override
    public CustomServiceResponseDTO detail(Long id) {
        CustomServiceResponseDTO foundCustomService = customServiceRepository.detail(id).orElseThrow(() -> {throw new RuntimeException();});
        return foundCustomService;
    }
}
