package com.openhelp.dictionary.service;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.country.CountryDto;
import com.openhelp.dictionary.dto.country.CountryFilterDto;

/**
 * @author Pavel Ravvich.
 */
public interface CountryService {

    ListDto<CountryDto> getList(CountryFilterDto filterDto);

    CountryDto findById(Long id);

    Long create(CountryDto countryDto);

    Long update(Long id, CountryDto countryDto);

    Long delete(Long id);
}
