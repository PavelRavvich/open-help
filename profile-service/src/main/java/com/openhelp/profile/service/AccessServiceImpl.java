package com.openhelp.profile.service;

import com.openhelp.profile.dto.access.UserAccessDto;
import com.openhelp.profile.mapper.AccessMapper;
import com.openhelp.profile.model.Access;
import com.openhelp.profile.model.User;
import com.openhelp.profile.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        User user = SecurityUtils.getSecurityContextUser();
        List<Access> accesses = user.getRoles()
                .stream()
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
