package com.app.travelmaker.entity.story;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Story Like Entity (스토리 좋아요)
 * */

@Entity
@Table(name = "TBL_STORY_LIKE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_STORY_LIKE SET DELETED = 1 WHERE ID = ?")
public class StroyLike extends Period {
    /**
     * Story Like PK (스토리 좋아요 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Member와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /**
     * Story와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;

    /**
     * Story LIKE Status (스토리 태그 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
