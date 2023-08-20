package com.app.travelmaker.domain.member.response;

import com.app.travelmaker.constant.MemberJoinAccountType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MemberJoinResponseDTO {

    private String memberEmail;
    private MemberJoinAccountType memberJoinAccountType;
}
