package app.romail.moodle_plus_plus.security;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Collection;
import java.util.Set;

public class SecurityAccount extends Account implements UserDetails {
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;
    public SecurityAccount(Account account) {
        this.username = String.valueOf(account.getId());
        this.password = account.getPassword();
        Set<GrantedAuthority> auths = new HashSet<>();

        for (Role role : account.getRoles()) {

            auths.add(new SimpleGrantedAuthority(role.toString()));
        }
        this.authorities = auths;
    }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
}

@Override
public String getPassword() {
    return password;
}

@Override
public String getUsername() {
    return username;
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
