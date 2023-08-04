package com.app.travelmaker.entity.eco;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.reply.Reply;
import com.app.travelmaker.entity.tag.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * EcoTag Entity (에코 인증 태그 중간 테이블)
 * */

@Entity
@Table(name = "TBL_ECO_TAG")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_ECO_TAG SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class EcoTag extends Tag {

    /**
     * Eco (Eco 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Eco eco;

    /**
     * EcoTag Status (에코 인증 태그 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
