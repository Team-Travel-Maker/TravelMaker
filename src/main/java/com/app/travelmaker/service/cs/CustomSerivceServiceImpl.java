package com.app.travelmaker.service.cs;

import com.app.travelmaker.domain.cs.CustomServiceDTO;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.cs.CustomServiceFile;
import com.app.travelmaker.repository.cs.CustomServiceFileRepository;
import com.app.travelmaker.repository.cs.CustomServiceRepository;
import com.app.travelmaker.repository.file.FileRepository;
import com.app.travelmaker.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomSerivceServiceImpl implements CustomSerivceService {

    private final CustomServiceRepository customServiceRepository;
    private final CustomServiceFileRepository customServiceFileRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    @Override
    public void register(CustomServiceDTO customServiceDTO) {

        /*나중에 세션으로 대체*/
        memberRepository.findById(1L).ifPresent(member -> {
            customServiceDTO.setMember(member);
        });

        /*문의 신고 저장 후 pk 값 가져오기*/
        Long id = customServiceRepository.save(toEntity(customServiceDTO)).getId();

        /*파일이 있으면 파일 저장*/
        if(customServiceDTO.getFiles().size() >0){
            CustomService foundCustomService = customServiceRepository.findById(id).orElseThrow(() -> {throw new RuntimeException();});
            /*찾은 CS 중간테이블 넣어 주기*/
            customServiceFileRepository.save(toEntity(foundCustomService));
            customServiceDTO.getFiles().stream().map(fileDTO -> toEntity(fileDTO)).forEach(fileRepository::save);
        }

    }

    @Override
    public CustomService findById(Long id) {
        CustomService foundCustomService = customServiceRepository.findById(id).orElseThrow(() -> {throw new RuntimeException();});
        return foundCustomService;
    }
}
