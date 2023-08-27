package com.app.travelmaker.entity.story;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.goWith.GoWith;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Story File Entity (스토리 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_STORY_FILE")
@Getter @ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_FILE SET DELETED = 1 WHERE ID = ?")
public class StoryFile extends File {

    /**
     * Story (스토리와 연관 관계) (N : 1)
     * */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;
}
