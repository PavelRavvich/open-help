package com.openhelp.dictionary.service;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.country.CountryDto;
import com.openhelp.dictionary.dto.country.CountryFilterDto;
import com.openhelp.dictionary.mapper.CountryMapper;
import com.openhelp.dictionary.model.Country;
import com.openhelp.dictionary.repository.CountryRepository;
import com.openhelp.dictionary.repository.CountryRepository.CountrySpecification;
import com.openhelp.dictionary.repository.filter.CountryFilter;
import com.openhelp.dictionary.utils.Utils;
import com.openhelp.dictionary.validation.NoSuchCountryException;
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
public class CountryServiceImpl implements CountryService {

    private final CountryMapper countryMapper;

    private final CountryRepository countryRepository;

    @Override
    public ListDto<CountryDto> getList(@NotNull CountryFilterDto filterDto) {
        log.info("Get Country list by filter: {}", filterDto);
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(),
                filterDto.getPageSize(), Utils.getSort(filterDto));
        CountryFilter filter = countryMapper.countryFilterDtoToCountryFilter(filterDto);
        Page<Country> page = countryRepository.findAll(
                new CountrySpecification(filter), pageable);
        List<CountryDto> items = page
                .map(countryMapper::countryToCountryDto)
                .getContent();
        return ListDto.<CountryDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
    }

    @Override
    public CountryDto findById(@NotNull Long id) {
        log.debug("Find Country by id: {}", id);
        return countryMapper.countryToCountryDto(
                countryRepository.findById(id)
                        .orElseThrow(NoSuchCountryException::new));
    }

    @Override
    public Long create(@NotNull CountryDto dto) {
        log.debug("Create new Country: {}", dto);
        return countryRepository.save(
                countryMapper.countryDtoToCountry(dto)
        ).getId();
    }

    @Override
    public Long update(@NotNull Long id, @NotNull CountryDto dto) {
        log.debug("Update Country id: {}, {}", id, dto);
        if (!countryRepository.existsById(id)) {
            throw new NoSuchCountryException();
        }
        return countryRepository.save(
                countryMapper.countryDtoToCountry(dto)
        ).getId();
    }

    @Override
    public Long delete(@NotNull Long id) {
        log.debug("Delete Country by id: {}", id);
        if (!countryRepository.existsById(id)) {
            throw new NoSuchCountryException();
        }
        countryRepository.deleteById(id);
        return id;
    }
}
