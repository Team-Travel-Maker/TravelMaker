package com.app.travelmaker.entity.story;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.goWith.GoWith;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Story File Entity (스토리 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_STORY_FILE")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_STORY_FILE SET DELETED = 1 WHERE ID = ?")
public class StoryFile extends File {

    /**
     * Story (스토리와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;

    /**
     * Story File Status (스토리 파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
