package com.app.travelmaker.entity.notice;


import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.cs.CustomServiceFile;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Notice Entity (공지 사항)
 * */

@Entity
@Table(name = "TBL_NOTICE")
@Getter @ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_NOTICE SET DELETED = 1 WHERE ID = ?")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice")
    private List<NoticeFile> noticeFiles = new ArrayList<>();

}
