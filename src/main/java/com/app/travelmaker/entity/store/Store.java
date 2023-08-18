package com.app.travelmaker.entity.store;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.constant.StoreStatus;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.entity.cs.CustomServiceFile;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.constant.StoreType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Store Entity (업체)
 * */

@Entity
@Table(name = "TBL_STORE")
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_STORE SET DELETED = 1 WHERE ID = ?")
public class Store extends Period {
    /**
     * Store PK (테마 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Store Title (업체 이름)
     * */
    @NotNull
    private String storeTitle;

    /**
     * Store Content (업체 설명)
     * */
    @NotNull private String storeContent;

    /**
     * Store Address (업체 주소)
     * */

    @Embedded @NotNull private Address address;

    @Enumerated(EnumType.STRING)
    @NotNull private StoreType storeType;

    @Enumerated(EnumType.STRING)
    @NotNull private StoreStatus storeStatus;

    /**
     * Store Status (업체 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

    /**
     * Member Entity 와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<StoreFile> storeFiles = new ArrayList<>();

}
