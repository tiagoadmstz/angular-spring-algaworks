package io.github.tiagoadmstz.algamoney.api.security.services;

import io.github.tiagoadmstz.algamoney.api.models.AppUser;
import io.github.tiagoadmstz.algamoney.api.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        AppUser appUser = user.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
        return new User(email, appUser.getPassword(), getPermitions(appUser));
    }

    private Collection<? extends GrantedAuthority> getPermitions(AppUser appUser) {
        Set<SimpleGrantedAuthority> authorities = new HashSet();
        appUser.getPermitions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getDescription()))
                .forEach(authorities::add);
        return authorities;
    }

}
