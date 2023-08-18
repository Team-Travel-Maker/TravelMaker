package com.app.travelmaker.service.mypage.company;

import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.store.Store;
import com.app.travelmaker.entity.store.StoreFile;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.repository.mypage.company.StoreFileRepository;
import com.app.travelmaker.repository.mypage.company.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreFileRepository storeFileRepository;
    private final MemberRepository memberRepository;

    @Override
    public void addStore(StoreDTO request) {
        // 세션으로 가져오기
        Member member = memberRepository.findById(32L)
                .orElseThrow(() -> new RuntimeException("사용자 없음 에러 발생"));

        // 업체 등록하기
        Store store = storeRepository.save(toStoreEntity(request, member));

        log.info("스토어에 등록된 아이디 : {}" , store.getId());

        // 업체 파일 등록
        List<StoreFile> storeFiles = storeFileRepository.saveAll(request.getFiles().stream().map(storeFileDTO -> toStoreFileEntity(storeFileDTO, store)).collect(Collectors.toList()));

    }

    @Override
    public List<StoreDTO> getAllStore() {
        // 세션으로 가져오기
        Member member = memberRepository.findById(32L)
                .orElseThrow(() -> new RuntimeException("사용자 없음 에러 발생"));
        return storeRepository.getAllStore(member.getId());
    }

    @Override
    public void deleteStore(List<Long> storeIds) {

        // 업체, 업체 파일 삭제
        storeIds.stream().forEach(id -> {
            storeRepository.findById(id)
                    .ifPresent(customService ->{
                        customService.getStoreFiles().forEach(file -> storeFileRepository.deleteById(file.getId()));
                    });
            storeRepository.deleteById(id);
        });

    }

    @Override
    public StoreDTO getStore(Long storeId) {
        return storeRepository.getStore(storeId);
    }
}
