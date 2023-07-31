package com.app.travelmaker.entity.eco;

import com.app.travelmaker.auditing.Period;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Member;

@Entity
@Getter @ToString
@Table(name="TBL_ECO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Eco extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String title;
    @NotNull private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
