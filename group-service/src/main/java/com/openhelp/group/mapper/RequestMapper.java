package com.openhelp.group.mapper;

import com.openhelp.group.dto.request.RequestDto;
import com.openhelp.group.dto.request.RequestFilterDto;
import com.openhelp.group.model.Request;
import com.openhelp.group.repository.filter.RequestFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class})
public interface RequestMapper {

    RequestFilter requestFilterDtoToRequestFilter(RequestFilterDto dto);

    RequestDto requestToRequestDto(Request request);

    @Mapping(target = "group", ignore = true)
    Request requestDtoToRequest(RequestDto dto);
}
