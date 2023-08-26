package com.app.travelmaker.service.notice;

import com.app.travelmaker.constant.FileType;
import com.app.travelmaker.domain.notice.request.NoticeRequestDTO;
import com.app.travelmaker.domain.notice.response.NoticeResponseDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.cs.CustomServiceFile;
import com.app.travelmaker.entity.notice.Notice;
import com.app.travelmaker.entity.notice.NoticeFile;
import com.app.travelmaker.repository.notice.NoticeFileRepository;
import com.app.travelmaker.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor =Exception.class)
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;

    @Override
    public Page<NoticeResponseDTO> getListWithPage(Pageable pageable) {
        return noticeRepository.getListWithPage(pageable);
    }


    @Override
    public void modifyNotice(NoticeRequestDTO noticeRequestDTO) {
        noticeRequestDTO.getDeleteFiles().forEach(id -> noticeFileRepository.deleteById(id));
        Notice foundNotice = noticeRepository.findById(noticeRequestDTO.getId()).orElseThrow(() -> {
            throw new RuntimeException();
        });
        noticeRequestDTO.setCreatedDate(foundNotice.getCreatedDate());

        log.info(foundNotice.getCreatedDate().toString());

        noticeRepository.save(toEntity(noticeRequestDTO));
        if(noticeRequestDTO.getFiles().size() >0){
            for (int i = 0; i < noticeRequestDTO.getFiles().size(); i++) {

                noticeFileRepository.save(NoticeFile.builder().notice(foundNotice)
                        .fileName(noticeRequestDTO.getFiles().get(i).getFileName())
                        .fileSize(noticeRequestDTO.getFiles().get(i).getFileSize())
                        /*공지사항은 내용 사진 밖에 없다 1개*/
                        .fileType(FileType.CONTENT_REPRESENTATIVE)
                        .fileUuid(noticeRequestDTO.getFiles().get(i).getFileUuid())
                        .filePath(noticeRequestDTO.getFiles().get(i).getFilePath())
                        .build());
            }
        }
    }

    @Override
    public NoticeResponseDTO detail(Long id) {
        return noticeRepository.detail(id).orElseThrow(() -> {throw new RuntimeException();});
    }

    @Override
    public void noticeDelete(List<Long> ids) {
        ids.stream().forEach(id -> {
            noticeRepository.findById(id)
                    .ifPresent(notice ->{
                        notice.getNoticeFiles().forEach(file -> noticeFileRepository.deleteById(file.getId()));
                    });
            noticeRepository.deleteById(id);
        });
    }

    @Override
    public void write(NoticeRequestDTO noticeRequestDTO) {

        Long id = noticeRepository.save(toEntity(noticeRequestDTO)).getId();

        if(noticeRequestDTO.getFiles().size() >0){
            for (int i = 0; i < noticeRequestDTO.getFiles().size(); i++) {

                Notice foundNotice = noticeRepository.findById(id).orElseThrow(() -> {
                    throw new RuntimeException();
                });
                noticeFileRepository.save(NoticeFile.builder().notice(foundNotice)
                        .fileName(noticeRequestDTO.getFiles().get(i).getFileName())
                        .fileSize(noticeRequestDTO.getFiles().get(i).getFileSize())
                        /*공지사항은 내용 사진 밖에 없다 1개*/
                        .fileType(FileType.CONTENT_REPRESENTATIVE)
                        .fileUuid(noticeRequestDTO.getFiles().get(i).getFileUuid())
                        .filePath(noticeRequestDTO.getFiles().get(i).getFilePath())
                        .build());
            }
        }
    }
}
