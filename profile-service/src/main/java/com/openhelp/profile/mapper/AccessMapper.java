package com.openhelp.profile.mapper;

import com.google.common.collect.Sets;
import com.openhelp.profile.dto.access.AccessDto;
import com.openhelp.profile.dto.access.AccessesDto;
import com.openhelp.profile.enums.OperationType;
import com.openhelp.profile.model.Access;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccessMapper {

    @Mapping(target = "roles", ignore = true)
    Access accessDtoToAccess(AccessDto dto);

    AccessDto accessToAccessDto(Access access);

    AccessesDto accessToAccessResponseDto(Long userId, Set<OperationType> operations);

    default Set<Access> accessIdsListToAccessList(List<Long> ids) {
        return Objects.isNull(ids)
                ? Sets.newHashSet()
                : ids.stream().map(id -> Access.builder().id(id).build()).collect(Collectors.toSet());
    }
}
