package com.app.travelmaker.entity.community;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.file.File;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_FILE SET DELETED = 1 WHERE ID = ?")
public class CommunityFile extends File {

    /**
     * Community (커뮤니티와 연관 관계) (N : 1)
     * */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;


}
