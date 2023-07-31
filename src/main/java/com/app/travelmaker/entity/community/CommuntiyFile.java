package com.app.travelmaker.entity.community;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.file.File;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * CommunityFile Entity (커뮤니티 파일 중간 테이블)
 * */

@Entity
@Table(name = "TBL_COMMUNITY_FILE")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_COMMUNITY_FILE SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class CommuntiyFile extends Period {

    /**
     * CommunityFile PK(커뮤니티 파일 중간 테이블 고유 번호)
     * */
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * CommunityFile FK(슈퍼키 서브키)
     * PK 이자 FK 연결 FILE 의 PK 와 연결됌 (N : 1)
     * */
    @MapsId
    @JoinColumn(name = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    /**
     * Community (커뮤니티와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;

    /**
     * CommunityFile Status (커뮤니티 파일  중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}
