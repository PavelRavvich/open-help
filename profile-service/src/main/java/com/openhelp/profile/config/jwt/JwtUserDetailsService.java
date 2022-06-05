package com.openhelp.profile.config.jwt;

import com.openhelp.profile.model.Role;
import com.openhelp.profile.model.User;
import com.openhelp.profile.service.UserService;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * @author Pavel Ravvich.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public JwtUser loadUserByUsername(final @NonNull String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = user.getRoles()
                .stream().map(Role::getSystemName).collect(toList())
                .stream().map(SimpleGrantedAuthority::new).collect(toList());
        return new JwtUser(user, authorities);
    }
}