package com.app.travelmaker.provider;

import com.app.travelmaker.constant.Role;
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
// 직렬화
// 객체를 세션에 담을 때 주소가 아닌 실제 필드의 데이터들을 담아놔야 한다.
// 이 때 XML 혹은 JSON형식으로 담기고 이를 직렬화 작업이라고 한다.
// 나중에 세션에서 다시 객체를 가져올 때에는 해당 데이터를 새로운 객체의 필드에 주입하며,
// 이를 역직렬화라고 한다.
public class MemberDetail implements UserDetails, Serializable {
    private Long id;
    private String memberId;
    private String memberPassword;
    private Role memberRole;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public MemberDetail(Long id, String memberId, String memberPassword, Role memberRole) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
        this.authorities = AuthorityUtils.createAuthorityList(memberRole.getSecurityRole());
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
