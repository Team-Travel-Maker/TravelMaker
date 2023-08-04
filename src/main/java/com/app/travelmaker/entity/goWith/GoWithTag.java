package com.app.travelmaker.entity.goWith;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.tag.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * GoWithTag Entity (같이 가요 태그 중간테이블)
 * */

@Entity
@Table(name = "TBL_GOWITH_TAG")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_GOWITH_TAG SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class GoWithTag extends Tag {

    /**
     * GoWith (같이 가요 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private GoWith goWith;

    /**
     * GoWithTag Status (같이 가요 태그 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
