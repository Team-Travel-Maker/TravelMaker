package com.app.travelmaker.domain.mypage.my.point;

import com.app.travelmaker.constant.PointCateGoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class MyPointDTO {
    private Long id;
    private String pointHistory;
    private PointCateGoryType pointCateGoryType;
    private Integer point;
    private Integer pointBalance;
    private LocalDateTime updatedDate;
}
