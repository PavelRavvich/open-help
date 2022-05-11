package com.openhelp.dictionary.mapper;

import com.openhelp.dictionary.dto.country.CountryDto;
import com.openhelp.dictionary.dto.country.CountryFilterDto;
import com.openhelp.dictionary.model.Country;
import com.openhelp.dictionary.repository.filter.CountryFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class})
public interface CountryMapper {

    @Mapping(target = "cities", ignore = true)
    Country countryDtoToCountry(CountryDto dto);

    CountryDto countryToCountryDto(Country country);

    CountryFilter countryFilterDtoToCountryFilter(CountryFilterDto dto);
}
