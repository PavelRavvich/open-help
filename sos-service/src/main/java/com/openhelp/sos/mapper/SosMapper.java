package com.openhelp.sos.mapper;

import com.openhelp.sos.dto.sos.SosDto;
import com.openhelp.sos.dto.sos.SosFilterDto;
import com.openhelp.sos.model.Sos;
import com.openhelp.sos.repository.filter.SosFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class})
public interface SosMapper {

    SosFilter sosFilterDtoToSosFilter(SosFilterDto dto);

    SosDto sosToSosDto(Sos sos);

    Sos sosDtoToSos(SosDto dto);
}
