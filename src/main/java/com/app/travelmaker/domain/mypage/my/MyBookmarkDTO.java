package com.app.travelmaker.domain.mypage.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class MyBookmarkDTO {
    private Long id;
    private Long themId;
    private String themeTitle;
    private String themeContent;
    private LocalDateTime themeStartDate;
    private LocalDateTime themeEndDate;
    private List<MyBookmarkFileDTO> files= new ArrayList<>();
}
