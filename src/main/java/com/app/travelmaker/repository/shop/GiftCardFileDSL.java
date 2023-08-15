package com.app.travelmaker.repository.shop;

import com.app.travelmaker.domain.file.FileDTO;

import java.util.List;

public interface GiftCardFileDSL {
    public List<FileDTO> getGiftCardFilesByGiftCardId(Long id);
}
