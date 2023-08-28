package com.app.travelmaker.entity.file;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.constant.FileType;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * File Entity (파일)
 * */


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_FILE")
@Getter @ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_FILE SET DELETED = 1 WHERE ID = ?")
public class File extends Period implements Serializable {
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
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    /**
     * FileSize(파일 사이즈)
     * */
    @NotNull private Long fileSize;

    /**
     * File Status (파일 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "file")
    private Member member;

  /*  @Builder
    public File(String fileName, String fileUuid,String filePath,  FileType fileType, Long fileSize) {
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }*/
}
