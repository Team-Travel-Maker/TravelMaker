package com.app.travelmaker.entity.eco;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.notice.Notice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Eco File Entity (에코 인증 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_ECO_FILE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_ECO_FILE SET DELETED = 1 WHERE ID = ?")
public class EcoFile extends File {

    /**
     * Eco (에코 인증 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Eco eco;

    /**
     * Eco File Status (에코 인증 파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
