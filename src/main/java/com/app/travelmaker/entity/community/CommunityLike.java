package com.app.travelmaker.entity.community;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.story.Story;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Community Like Entity (커뮤니티 좋아요)
 * */

@Entity
@Table(name = "TBL_COMMUNITY_LIKE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_COMMUNITY_LIKE SET DELETED = 1 WHERE ID = ?")
public class CommunityLike extends Period {
    /**
     * Community Like PK (커뮤니티 좋아요 고유 번호)
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
     * Community 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;

    /**
     * Community LIKE Status (커뮤니티 태그 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
