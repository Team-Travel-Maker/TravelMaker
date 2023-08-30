package com.app.travelmaker.service.mypage.company;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.store.Store;
import com.app.travelmaker.entity.store.StoreFile;
import com.app.travelmaker.repository.mypage.company.StoreFileRepository;
import com.app.travelmaker.repository.mypage.company.StoreRepository;
import com.app.travelmaker.service.MemberSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreServiceImpl extends AccountSupport implements StoreService, MemberSupport {

    private final StoreRepository storeRepository;
    private final StoreFileRepository storeFileRepository;

    @Override
    public void addStore(StoreDTO request) {
        Member member = toMemberEntity(authenticationInfo());
        // 업체 등록하기
        request.setStoreResult("");
        Store store = storeRepository.save(toStoreEntity(request, member));

        log.info("스토어에 등록된 아이디 : {}" , store.getId());

        // 업체 파일 등록
        List<StoreFile> storeFiles = storeFileRepository.saveAll(request.getFiles().stream().map(storeFileDTO -> toStoreFileEntity(storeFileDTO, store)).collect(Collectors.toList()));

    }

    @Override
    public List<StoreDTO> getAllStore() {
        Member member = toMemberEntity(authenticationInfo());
        // 세션으로 가져오기
        return storeRepository.getAllStore(member.getId());
    }

    @Override
    public Page<StoreDTO> getStoreWithPage(Pageable pageable) {
        Long memberId = authenticationInfo().getId();
        return storeRepository.getStoreWithPage(pageable, memberId);
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

    @Override
    public void updateStore(StoreDTO request) {
        Member member = toMemberEntity(authenticationInfo());
        Store foundStore = storeRepository.findById(request.getId()).get();

        // 업체 수정하기
        foundStore.setStoreTitle(request.getStoreTitle());
        foundStore.setStoreContent(request.getStoreContent());
        foundStore.setStoreResult(request.getStoreResult());
        foundStore.setStoreStatus(request.getStoreStatus());
        foundStore.setStoreType(request.getStoreType());
        foundStore.getAddress().setAddress(request.getAddress().getAddress());
        foundStore.getAddress().setAddressDetail(request.getAddress().getAddressDetail());
        foundStore.getAddress().setPostcode(request.getAddress().getPostcode());

        //storeRepository.save(foundStore);

        if(request.getFiles().size() != 0) {
            storeFileRepository.deleteAllByStoreId(request.getId());
            // 새로운 업체 파일 등록
            List<StoreFile> storeFiles = storeFileRepository.saveAll(request.getFiles()
                    .stream()
                    .filter(storeFileDTO -> storeFileDTO.getFileName()!=null)
                    .map(storeFileDTO -> toStoreFileEntity(storeFileDTO, foundStore))
                    .collect(Collectors.toList()));
        }
    }
}
