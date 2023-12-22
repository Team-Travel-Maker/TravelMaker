package com.app.travelmaker.domain.community;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data @ToString
@NoArgsConstructor
@Slf4j
public class ReplyDTO {

    private Long id;
    private String comment;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    

    public ReplyDTO(Long id, String comment, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.comment = comment;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
