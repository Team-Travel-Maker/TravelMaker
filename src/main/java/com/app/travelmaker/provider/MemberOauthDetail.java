package com.app.travelmaker.provider;

import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@ToString
@Getter
public class MemberOauthDetail extends DefaultOAuth2User {

    private final MemberResponseDTO memberResponseDTO;



    public MemberOauthDetail(MemberResponseDTO memberResponseDTO, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        super(authorities, attributes, nameAttributeKey);
        this.memberResponseDTO = memberResponseDTO;
    }

}
