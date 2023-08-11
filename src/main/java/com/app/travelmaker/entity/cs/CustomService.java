package com.app.travelmaker.entity.cs;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.constant.CsType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class CustomService extends Period implements Serializable {

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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /**
     * Custom Service Status (문의/신고 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customService")
    private List<CustomServiceFile> customServiceFile = new ArrayList<>();



/*    @Builder
    public CustomService(@NotNull String csTitle, @NotNull String csContent, @NotNull CsType csType, Member member) {
        this.csTitle = csTitle;
        this.csContent = csContent;
        this.csType = csType;
        this.member = member;
    }*/
}
