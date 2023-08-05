package com.app.travelmaker.entity.store;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.theme.Theme;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Store File Entity (업체 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_STORE_FILE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_STORE_FILE SET DELETED = 1 WHERE ID = ?")
public class StoreFile extends File {

    /**
     * Store (업체 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    /**
     * Store File Status (업체 파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
