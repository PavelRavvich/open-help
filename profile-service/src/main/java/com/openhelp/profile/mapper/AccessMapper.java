package com.openhelp.profile.mapper;

import com.google.common.collect.Sets;
import com.openhelp.profile.model.Access;
import com.openhelp.profile.utils.SecurityUtils;
import com.openhelp.profile.utils.SecurityUtils.UserAccessDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.openhelp.profile.utils.SecurityUtils.AccessDto;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccessMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", ignore = true)
    Access accessDtoToAccess(AccessDto dto);

    AccessDto accessToAccessDto(Access access);

    UserAccessDto toUserAccessDto(Long userId, List<Access> accesses);

    default Set<Access> accessIdsListToAccessList(List<Long> ids) {
        return Objects.isNull(ids)
                ? Sets.newHashSet()
                : ids.stream().map(id -> Access.builder().id(id).build()).collect(Collectors.toSet());
    }
}
