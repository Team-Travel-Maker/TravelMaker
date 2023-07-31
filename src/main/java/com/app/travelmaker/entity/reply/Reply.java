package com.app.travelmaker.entity.reply;

import com.app.travelmaker.auditing.Period;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter @ToString
@Table(name="TBL_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String content;
    @NotNull private Long group;
    @NotNull private Long depth;
}
