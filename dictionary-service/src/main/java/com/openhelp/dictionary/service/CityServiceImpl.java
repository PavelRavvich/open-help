package com.openhelp.dictionary.service;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.city.CityDto;
import com.openhelp.dictionary.dto.city.CityFilterDto;
import com.openhelp.dictionary.mapper.CityMapper;
import com.openhelp.dictionary.model.City;
import com.openhelp.dictionary.repository.CityRepository;
import com.openhelp.dictionary.repository.CityRepository.CitySpecification;
import com.openhelp.dictionary.repository.filter.CityFilter;
import com.openhelp.dictionary.utils.Utils;
import com.openhelp.dictionary.validation.NoSuchCityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;

    private final CityRepository cityRepository;

    @Override
    public ListDto<CityDto> getList(@NotNull CityFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(),
                filterDto.getPageSize(), Utils.getSort(filterDto));
        CityFilter filter = cityMapper.cityFilterDtoToCity(filterDto);
        Page<City> page = cityRepository.findAll(
                new CitySpecification(filter), pageable);
        List<CityDto> items = page
                .map(cityMapper::cityToCityDto)
                .getContent();

        log.info("Get City list {}", items);
        return ListDto.<CityDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
    }

    @Override
    public CityDto findById(@NotNull Long id) {
        log.debug("Find City by id: {}", id);
        return cityMapper.cityToCityDto(cityRepository.findById(id)
                .orElseThrow(NoSuchCityException::new));
    }

    @Override
    public Long create(@NotNull CityDto dto) {
        log.debug("Create new City: {}", dto);
        return cityRepository.save(
                cityMapper.cityDtoToCity(dto)
        ).getId();
    }

    @Override
    public Long update(@NotNull Long id, @NotNull CityDto dto) {
        log.debug("Update City id: {}, {}", id, dto);
        if (!cityRepository.existsById(id)) {
            throw new NoSuchCityException();
        }
        return cityRepository.save(
                cityMapper.cityDtoToCity(dto)
        ).getId();
    }

    @Override
    public Long delete(@NotNull Long id) {
        log.debug("Delete City by id: {}", id);
        if (!cityRepository.existsById(id)) {
            throw new NoSuchCityException();
        }
        cityRepository.deleteById(id);
        return id;
    }
}
