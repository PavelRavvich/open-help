package com.openhelp.profile.service;

import com.openhelp.profile.config.jwt.JwtUser;
import com.openhelp.profile.mapper.AccessMapper;
import com.openhelp.profile.model.Access;
import com.openhelp.profile.model.User;
import com.openhelp.profile.validation.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.openhelp.profile.utils.SecurityUtils.UserAccessDto;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    private final AccessMapper accessMapper;

    @Override
    public UserAccessDto getAccesses() {
        Authentication auth = Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(AccessDeniedException::new);
        User user = ((JwtUser) auth.getPrincipal()).getUser();
        List<Access> accesses = user.getRoles().stream()
                .flatMap(role -> role.getAccesses().stream())
                .filter(distinctByKeys(Access::getOperationType, Access::getEntityType))
                .collect(Collectors.toList());
        return accessMapper.toUserAccessDto(user.getId(), accesses);
    }

    @NotNull
    @SafeVarargs
    private <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
        Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
        return item -> {
            List<?> keys = Arrays.stream(keyExtractors)
                    .map(key -> key.apply(item))
                    .collect(Collectors.toList());
            return Objects.isNull(seen.putIfAbsent(keys, Boolean.TRUE));
        };
    }
}
