package com.app.travelmaker.entity.giftcard;

import com.app.travelmaker.entity.community.Community;
import com.app.travelmaker.entity.file.File;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * GiftCardFile Entity (커뮤니티 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_GIFT_CARD_FILE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_GIFT_CARD_FILE SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class GiftCardFile {
    /**
     * GiftCardFile PK(상품권 파일 중간 테이블 고유 번호)
     * */
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * GiftCardFile FK(슈퍼키 서브키)
     * PK 이자 FK 연결 FILE 의 PK 와 연결됌 (N : 1)
     * */
    @MapsId
    @JoinColumn(name = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    /**
     * GiftCard (상품권과 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private GiftCard giftCard;

    /**
     * GiftCardFile Status (상품권 파일 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
