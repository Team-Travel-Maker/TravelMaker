package com.app.travelmaker.entity.cs;

import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.file.File;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Custom Service File Entity (문의 신고 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_CUSTOM_SERVICE_FILE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_CUSTOM_SERVICE_FILE SET DELETED = 1 WHERE ID = ?")
public class CustomServiceFile extends File {

    /**
     * Custom Service (문의 신고 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomService customService;

    /**
     * Custom Service File Status (문의 신고파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
