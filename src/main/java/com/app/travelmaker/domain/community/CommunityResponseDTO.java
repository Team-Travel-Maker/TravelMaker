package com.app.travelmaker.domain.community;

import com.app.travelmaker.constant.CommunityType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class CommunityResponseDTO {
    private Long id;
    private String communityTitle;
    private String communityContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private CommunityType communityCategory;
    private String memberEmail;
    private List<CommunityFileResponseDTO> files = new ArrayList<>();
}
