package com.app.travelmaker.entity.theme;

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
 * Theme File Entity (테마 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_THEME_FILE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_THEME_FILE SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class ThemeFile extends File {

    /**
     * Theme (테마 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Theme theme;

    /**
     * Theme File Status (테마 파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
