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
@Where(clause = "DELETED = 0")
public class NoticeFile extends Period {

    /**
     * Notice File PK(고유 번호)
     * */
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Notice File FK(슈퍼키 서브키)
     * PK 이자 FK 연결 FILE 의 PK 와 연결됌 (N : 1)
     * */
    @MapsId
    @JoinColumn(name = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

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
