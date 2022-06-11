package com.openhelp.profile.service;

import com.openhelp.profile.dto.access.AccessesDto;
import com.openhelp.profile.enums.EntityType;
import com.openhelp.profile.enums.OperationType;
import com.openhelp.profile.mapper.AccessMapper;
import com.openhelp.profile.model.Access;
import com.openhelp.profile.model.Role;
import com.openhelp.profile.model.User;
import com.openhelp.profile.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
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
    public AccessesDto getAccesses(@NotNull EntityType entityType) {
        User user = SecurityUtils.getSecurityContextUser();
        Set<OperationType> operations = user
                .getRoles()
                .stream()
                .map(Role::getAccesses)
                .flatMap(Collection::stream)
                .filter(access -> entityType == access.getEntityType())
                .map(Access::getOperationType)
                .collect(Collectors.toSet());
        return accessMapper.accessToAccessResponseDto(user.getId(), operations);
    }
}
