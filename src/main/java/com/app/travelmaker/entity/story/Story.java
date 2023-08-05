package com.app.travelmaker.entity.story;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Story Entity (스토리)
 * */

@Entity
@Table(name = "TBL_STORY")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_STORY SET DELETED = 1 WHERE ID = ?")
public class Story extends Period {

    /**
     * Story PK (스토리 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     *  Story Title(스토리 제목)
     * */
    @NotNull private String storyTitle;

    /**
     * Story Content (스토리 내용)
     * */
    @NotNull private String storyContent;


    /**
     * Story Status (스토리 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

    /**
     * Member Entity 와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
