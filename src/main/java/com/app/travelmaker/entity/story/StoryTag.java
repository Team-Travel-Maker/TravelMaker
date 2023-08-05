package com.app.travelmaker.entity.story;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.goWith.GoWith;
import com.app.travelmaker.entity.tag.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * StoryTag Entity (스토리 태그 중간테이블)
 * */

@Entity
@Table(name = "TBL_STORY_TAG")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_STORY_TAG SET DELETED = 1 WHERE ID = ?")
public class StoryTag extends Tag {

    /**
     * StoryTag (스토리와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;

    /**
     * StoryTag Status (스토리 태그 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
