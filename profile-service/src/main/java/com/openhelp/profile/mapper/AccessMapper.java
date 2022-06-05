package com.openhelp.profile.mapper;

import com.openhelp.profile.dto.access.AccessDto;
import com.openhelp.profile.model.Access;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccessMapper {

    Access accessDtoToAccess(AccessDto dto);

    AccessDto accessToAccessDto(Access access);
}
