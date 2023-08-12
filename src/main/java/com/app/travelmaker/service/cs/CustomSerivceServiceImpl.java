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

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomSerivceServiceImpl implements CustomSerivceService {

    private final CustomServiceRepository customServiceRepository;
    private final CsAnswerRepository csAnswerRepository;
    private final CustomServiceFileRepository customServiceFileRepository;
    private final MemberRepository memberRepository;


    @Override
    public Page<CustomServiceResponseDTO> getList(Pageable pageable) {
        return customServiceRepository.getListWithPage(pageable);
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
        /*나중에 세션으로 대체*/
        memberRepository.findById(1L).ifPresent(member -> {
            customServiceDTO.setMember(member);
        });

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

    @Override
    public CustomServiceResponseDTO detail(Long id) {
        CustomServiceResponseDTO foundCustomService = customServiceRepository.detail(id).orElseThrow(() -> {throw new RuntimeException();});
        return foundCustomService;
    }
}
