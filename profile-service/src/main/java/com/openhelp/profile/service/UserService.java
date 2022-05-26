package com.openhelp.profile.service;

import com.openhelp.profile.config.jwt.JwtUser;
import com.openhelp.profile.dto.ListDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.dto.user.UserDto;
import com.openhelp.profile.dto.user.UserFilterDto;
import com.openhelp.profile.enums.RoleType;
import com.openhelp.profile.model.Role;
import com.openhelp.profile.model.User;
import org.jetbrains.annotations.NotNull;
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

    Long signUp(SignUpRequestDto sign);

    Long create(User user, boolean isEnabled);

    String activateUser(UUID activationCode);

    Long update(Long userId, SignUpRequestDto signUp);

    User getUserByUsername(String username);

    ListDto<UserDto> list(UserFilterDto filter);

    UserDto findById(Long userId);

    Long updatePassword(Long userId, String oldPassword, String newPassword);

    Long updateNickname(Long userId, String nickname);

    Long delete(Long userId);
}