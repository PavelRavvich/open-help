package com.openhelp.dictionary.service;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.city.CityDto;
import com.openhelp.dictionary.dto.city.CityFilterDto;

/**
 * @author Pavel Ravvich.
 */
public interface CityService {

    ListDto<CityDto> getList(CityFilterDto filterDto);

    CityDto findById(Long id);

    Long create(CityDto cityDto);

    Long update(Long id, CityDto cityDto);

    Long delete(Long id);
}
