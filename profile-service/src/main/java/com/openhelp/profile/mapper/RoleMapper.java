package com.openhelp.profile.mapper;

import com.google.common.collect.Lists;
import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.dto.role.RoleFilterDto;
import com.openhelp.profile.dto.role.RoleItemDto;
import com.openhelp.profile.dto.role.RoleRequestDto;
import com.openhelp.profile.model.Role;
import com.openhelp.profile.repository.filter.RoleFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AccessMapper.class})
public interface RoleMapper {

    RoleDto roleToRoleDto(Role role);

    RoleFilter toRoleFilter(RoleFilterDto dto);

    default List<Role> roleIdsListToRoleList(List<Long> ids) {
        return Objects.isNull(ids)
                ? Lists.newArrayList()
                : ids.stream().map(id -> Role.builder().id(id).build()).collect(Collectors.toList());
    }

    @Mapping(target = "users", ignore = true)
    Role roleDtoToRole(RoleDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(source = "accessIds", target = "access")
    Role roleRequestDtoToRole(RoleRequestDto role);

    RoleItemDto roleToRoleItemDto(Role role);
}
