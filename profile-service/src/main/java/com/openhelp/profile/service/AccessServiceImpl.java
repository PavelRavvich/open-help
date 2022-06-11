package com.openhelp.profile.service;

import com.openhelp.profile.dto.access.AccessRequestDto;
import com.openhelp.profile.dto.access.AccessResponseDto;
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
    public AccessResponseDto getAccesses(@NotNull AccessRequestDto req) {
        User user = SecurityUtils.getSecurityContextUser();
        Set<OperationType> operations = user
                .getRoles()
                .stream()
                .map(Role::getAccesses)
                .flatMap(Collection::stream)
                .filter(access -> access.getEntityType() == req.getEntityType())
                .map(Access::getOperationType)
                .collect(Collectors.toSet());
        return accessMapper.accessToAccessResponseDto(user.getId(), operations);
    }
}
