package com.nsia.officems._identity.authentication.user;

import java.util.Collection;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class CustomUser extends User {

    private static final long serialVersionUID = -3531439484732724601L;

    private String currentLang;
    private Long workFlowId;
    private Long departmentId;
    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
            String currentLang, Long workFlowId, Long departmentId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.currentLang = currentLang;
        this.workFlowId = workFlowId;
        this.departmentId = departmentId;
    }

}
