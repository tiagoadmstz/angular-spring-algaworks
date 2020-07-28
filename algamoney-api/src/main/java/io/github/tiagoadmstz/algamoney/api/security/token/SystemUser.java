package io.github.tiagoadmstz.algamoney.api.security.token;

import io.github.tiagoadmstz.algamoney.api.models.AppUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SystemUser extends User {

    private static final long serialVersionUID = 4970473773272955507L;
    @Getter
    private AppUser appUser;

    public SystemUser(AppUser appUser, Collection<? extends GrantedAuthority> authorities) {
        super(appUser.getEmail(), appUser.getPassword(), authorities);
        this.appUser = appUser;
    }

}
