package com.openhelp.profile.repository.filter;

import com.openhelp.profile.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    private String username;

    private String nickname;

    private Boolean isEnabled;

    private List<Role> roles;

    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

    public Optional<String> getNickname() {
        return Optional.ofNullable(nickname);
    }

    public Optional<Boolean> isEnabled() {
        return Optional.ofNullable(isEnabled);
    }

    public Optional<List<Role>> getRoles() {
        return roles.isEmpty() ? Optional.empty() : Optional.of(roles);
    }
}
