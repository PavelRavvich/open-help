package com.openhelp.profile.service;

import com.openhelp.profile.config.jwt.JwtUser;
import com.openhelp.profile.enums.RoleType;
import com.openhelp.profile.model.Role;
import com.openhelp.profile.model.User;
import com.openhelp.profile.repository.filter.UserFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
public interface UserService {

    default Long getSecurityContextUserId() {
        return getSecurityContextUser().getId();
    }

    default User getSecurityContextUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication) ? ((JwtUser) authentication.getPrincipal()).getUser() : null;
    }

    default Boolean anyMatchCredentials(@NotNull RoleType... candidates) {
        List<String> roles = Arrays.stream(candidates).map(RoleType::getType).collect(Collectors.toList());
        return getSecurityContextUser().getRoles().stream().map(Role::getSystemName).anyMatch(roles::contains);
    }

    Long create(User user, boolean isEnabled);

    String activateUser(UUID activationCode);

    Long update(Long userId, User user);

    User getUserByUsername(String username);

    Page<User> list(Pageable pagination, UserFilter filter);

    User findById(Long userId);

    Long updatePassword(Long userId, String oldPassword, String newPassword);

    Long updateNickname(Long userId, String nickname);

    Long delete(Long userId);
}