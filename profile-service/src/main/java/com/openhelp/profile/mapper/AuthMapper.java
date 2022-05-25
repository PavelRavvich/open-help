package com.openhelp.profile.mapper;

import com.openhelp.profile.dto.auth.AuthResponseDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class, RoleMapper.class})
public interface AuthMapper {

    AuthResponseDto toAuthResponseDto(String accessToken, User user);

    @Mapping(source = "roleIds", target = "roles")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reputation", ignore = true)
    @Mapping(target = "activationCode", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    User signUpRequestDtoToUser(SignUpRequestDto dto);
}
