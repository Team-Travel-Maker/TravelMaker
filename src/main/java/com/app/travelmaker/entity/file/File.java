package com.app.travelmaker.entity.file;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.type.FileType;
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
 * File Entity (파일)
 * */


@Entity
@Table(name = "TBL_FILE")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_FILE SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class File extends Period {
    /**
     * File PK(고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * FileName(파일 이름)
     * */
   @NotNull private String fileName;

    /**
     * FileSize(파일 Uuid)
     * */
    @NotNull private String fileUuid;

    /**
     * FilePath(파일 경로)
     * */
    @NotNull private String filePath;

    /**
     *   FileType
     *   REPRESENTATIVE : 대표 이미지
     *   NON_REPRESENTATIVE : 제목 이미지
     *   CONTENT_REPRESENTATIVE : 내용 이미지
     *
     * */
    @NotNull private FileType fileType;

    /**
     * FileSize(파일 사이즈)
     * */
    @NotNull private Long fileSize;

    /**
     * File Status (파일 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

}
