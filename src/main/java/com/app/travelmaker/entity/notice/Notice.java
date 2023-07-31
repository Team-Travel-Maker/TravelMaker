package com.app.travelmaker.entity.notice;


import com.app.travelmaker.auditing.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Notice Entity (공지 사항)
 * */

@Entity
@Table(name = "TBL_NOTICE")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_NOTICE SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Notice extends Period {

    /**
     * Notice PK(고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Notice_TITLE(공지 제목)
     * */
    @NotNull private String noticeTitle;

    /**
     * Notice_CONTENT(공지 내용)
     * */
    @NotNull private String noticeContent;

    /**
     * Notice Status (공지 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

}
