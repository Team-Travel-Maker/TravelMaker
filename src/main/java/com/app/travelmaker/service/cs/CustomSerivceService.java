package com.app.travelmaker.service.cs;

import com.app.travelmaker.domain.cs.request.CustomServiceDTO;
import com.app.travelmaker.domain.cs.request.CsAnswerDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.cs.CsAnswer;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.service.MemberSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomSerivceService extends MemberSupport {

    /*문의*/
    public Page<CustomServiceResponseDTO> getList(Pageable pageable, String keyword);

    public CustomServiceResponseDTO detail(Long id);

    public void register(CustomServiceDTO customServiceDTO);

    public void inquiryDelete(List<Long> ids);


    /*답변*/

    public void answerRegister(CsAnswerDTO csAnswerDTO);

    public void answerModify(CsAnswerDTO csAnswerDTO);


    /*문의*/
    public default CustomService toEntity(CustomServiceDTO customServiceDTO){
        return CustomService.builder()
                            .csTitle(customServiceDTO.getCsTitle())
                            .csContent(customServiceDTO.getCsContent())
                            .csType(customServiceDTO.getCsType())
                            .member(toMemberEntity(customServiceDTO.getMemberResponseDTO()))
                            .build();

    }

    /*답변*/
    public default CsAnswer toEntity(CsAnswerDTO csAnswerDTO){
        return CsAnswer.builder()
                .id(csAnswerDTO.getId())
                .answerContent(csAnswerDTO.getAnswerContent())
                .createdDate(csAnswerDTO.getCreatedDate())
                .customService(csAnswerDTO.getCustomService())
                .build();
    }



    public default File toEntity(FileDTO fileDTO){
        return File.builder()
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileSize(fileDTO.getFileSize())
                .fileUuid(fileDTO.getFileUuid())
                .fileType(fileDTO.getFileType())
                .build();
    }
}
