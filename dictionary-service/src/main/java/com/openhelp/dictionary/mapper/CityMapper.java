package com.openhelp.dictionary.mapper;

import com.openhelp.dictionary.dto.city.CityDto;
import com.openhelp.dictionary.dto.city.CityFilterDto;
import com.openhelp.dictionary.model.City;
import com.openhelp.dictionary.repository.filter.CityFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class})
public interface CityMapper {

    @Mapping(target = "country.id", source = "countryId")
    City cityDtoToCity(CityDto dto);

    @Mapping(target = "countryId", source = "country.id")
    CityDto cityToCityDto(City city);

    @Mapping(target = "country.id", source = "countryId")
    CityFilter cityFilterDtoToCity(CityFilterDto dto);
}
