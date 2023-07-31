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
@Where(clause = "DELETED = 0")

public class StoryFile extends Period {

    /**
     * Story File PK(고유 번호)
     * */
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Story File FK(슈퍼키 서브키)
     * PK 이자 FK 연결 FILE 의 PK 와 연결됌 (N : 1)
     * */
    @MapsId
    @JoinColumn(name = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

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
