package com.app.travelmaker.provider;

import com.app.travelmaker.constant.Role;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

@Component
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail implements UserDetails, Serializable {
    private Long id;
    private String memberId;
    private String memberPassword;
    private Role memberRole;
    private Collection<? extends GrantedAuthority> authorities;
    private MemberResponseDTO memberResponseDTO;

    @Builder
    public MemberDetail(Long id, String memberId, String memberPassword, Role memberRole, MemberResponseDTO memberResponseDTO) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
        this.authorities = AuthorityUtils.createAuthorityList(memberRole.getSecurityRole());
        this.memberResponseDTO = memberResponseDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
