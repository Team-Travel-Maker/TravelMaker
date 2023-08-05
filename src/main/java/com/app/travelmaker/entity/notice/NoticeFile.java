package com.app.travelmaker.entity.notice;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.file.File;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Notice File Entity (공지 사항 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_NOTICE_FILE")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_NOTICE_FILE SET DELETED = 1 WHERE ID = ?")
public class NoticeFile extends File {

    /**
     * Notice (NOTICE 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    /**
     * Notice File Status (공지 파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

}
