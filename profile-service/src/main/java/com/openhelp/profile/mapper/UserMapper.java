package com.openhelp.profile.mapper;

import com.openhelp.profile.dto.user.UserDto;
import com.openhelp.profile.dto.user.UserDumbDto;
import com.openhelp.profile.dto.user.UserFilterDto;
import com.openhelp.profile.model.User;
import com.openhelp.profile.repository.filter.UserFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class, RoleMapper.class})
public interface UserMapper {

    UserDto userToUserDto(User user);

    UserDumbDto userToUserDumbDto(User user);

    @Mapping(source = "roleIds", target = "roles")
    UserFilter toUserFilter(UserFilterDto dto);

}
