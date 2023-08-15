package com.app.travelmaker.entity.cs;

import com.app.travelmaker.constant.FileType;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.file.File;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Custom Service File Entity (문의 신고 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_CUSTOM_SERVICE_FILE")
@Getter
@ToString(exclude = "customService")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_FILE SET DELETED = 1 WHERE ID = ?")
public class CustomServiceFile extends File implements Serializable {

    /**
     * Custom Service (문의 신고 와 연관 관계) (N : 1)
     * */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomService customService;

}
