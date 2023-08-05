package com.app.travelmaker.entity.goWith;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.file.File;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * GoWith File Entity (같이 가요 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_GOWITH_FILE")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_GOWITH_FILE SET DELETED = 1 WHERE ID = ?")
public class GoWithFile extends File {

    /**
     * GoWith (같이 가요와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private GoWith goWith;

    /**
     * GoWith File Status (같이 가요 파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
