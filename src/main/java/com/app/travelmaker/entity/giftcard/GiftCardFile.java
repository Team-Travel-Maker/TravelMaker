package com.app.travelmaker.entity.giftcard;

import com.app.travelmaker.entity.community.Community;
import com.app.travelmaker.entity.file.File;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * GiftCardFile Entity (커뮤니티 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_GIFT_CARD_FILE")
@Getter
@ToString(exclude = "giftCard")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_GIFT_CARD_FILE SET DELETED = 1 WHERE ID = ?")
public class GiftCardFile extends File {

    /**
     * GiftCard (상품권과 연관 관계) (N : 1)
     * */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private GiftCard giftCard;

}
