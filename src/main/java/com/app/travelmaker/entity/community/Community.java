package com.app.travelmaker.entity.community;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.story.StoryFile;
import com.app.travelmaker.entity.tag.Tag;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Community Entity (커뮤니티)
 * */

@Entity
@Table(name = "TBL_COMMUNITY")
@Getter @ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_COMMUNITY SET DELETED = 1 WHERE ID = ?")
public class Community extends Period {

    /**
     * Community PK (커뮤니티 고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Community Title (커뮤니티 제목)
     * */
    @NotNull private String communityTitle;

    /**
     * Community Content (커뮤니티 내용)
     * */
    @NotNull private String communityContent;

    /**
     * Community Category (커뮤니티 카테고리)
     * */
    @Enumerated(EnumType.STRING)
    @ColumnDefault("REVIEW")
    @NotNull private CommunityType communityCategory;

    /**
     * Community Status (커뮤니티 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;


    /**
     * Member 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    private List<CommunityFile> communityFiles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "TBL_TAG",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();



}
