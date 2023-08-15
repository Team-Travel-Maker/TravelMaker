package com.app.travelmaker.repository.cs;

import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomServiceDSL {

    public Page<CustomServiceResponseDTO> getListWithPage(Pageable pageable);

    public Optional<CustomServiceResponseDTO> detail(Long id);
}
