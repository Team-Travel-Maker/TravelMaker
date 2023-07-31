package com.app.travelmaker.entity.goWith;

import com.app.travelmaker.type.MbtiType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Member;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_GOWITH")
public class GoWith {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String title;
    @NotNull private String content;
    @NotNull @Enumerated(EnumType.STRING)
    private MbtiType mbti;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
