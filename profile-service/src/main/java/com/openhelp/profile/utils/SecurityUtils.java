package com.openhelp.profile.utils;

import com.openhelp.profile.config.jwt.JwtUser;
import com.openhelp.profile.enums.RoleType;
import com.openhelp.profile.model.Role;
import com.openhelp.profile.model.User;
import com.openhelp.profile.validation.AccessDeniedException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
public class SecurityUtils {

    public static Long getSecurityContextUserId() {
        return Objects.requireNonNull(getSecurityContextUser()).getId();
    }

    @NotNull
    public static User getSecurityContextUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            return ((JwtUser) authentication.getPrincipal()).getUser();
        } else {
            throw new AccessDeniedException();
        }
    }

    @NotNull
    public static Boolean anyMatchCredentials(@NotNull RoleType... candidates) {
        List<String> roles = Arrays.stream(candidates).map(RoleType::getType).collect(Collectors.toList());
        return Objects.requireNonNull(getSecurityContextUser())
                .getRoles()
                .stream()
                .map(Role::getSystemName)
                .anyMatch(roles::contains);
    }
}
