package com.app.travelmaker.entity.cs;

import com.app.travelmaker.auditing.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Custom Service Answer Entity (문의/신고 답변)
 * */
@Entity
@Table(name = "TBL_CS_ANSWER")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_CS_ANSWER SET DELETED = 1 WHERE ID = ?")
public class CsAnswer extends Period {
    /**
     * Custom Service Answer PK (문의/신고 답변 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Custom Service Answer Content (문의/신고 답변 내용)
     * */
    @NotNull private String answerContent;

    /**
     * Custom Service와 연관관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomService customService;

    /**
     * Custom Service Answer Stauts (문의/신고 답변 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
