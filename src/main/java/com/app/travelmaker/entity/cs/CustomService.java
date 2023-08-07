package com.app.travelmaker.entity.cs;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.constant.CsType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Custom Service Entity (문의/신고)
 * */

@Entity
@Table(name = "TBL_CUSTOM_SERVICE")
@Getter
@ToString
@SuperBuilder
@SQLDelete(sql = "UPDATE TBL_CUSTOM_SERVICE SET DELETED = 1 WHERE ID = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomService extends Period {

    /**
     * Custom Service PK (문의/신고  고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Custom Service Title (문의/신고 제목)
     * */
    @NotNull private String csTitle;

    /**
     * Custom Service Content (문의/신고 내용)
     * */
    @NotNull private String csContent;

    /**
     * Custom Service Type (문의/신고)
     * */
    @Enumerated(EnumType.STRING)
    @NotNull private CsType csType;

    /**
     * Member 와 연관관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /**
     * Custom Service Status (문의/신고 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

/*    @Builder
    public CustomService(@NotNull String csTitle, @NotNull String csContent, @NotNull CsType csType, Member member) {
        this.csTitle = csTitle;
        this.csContent = csContent;
        this.csType = csType;
        this.member = member;
    }*/
}
