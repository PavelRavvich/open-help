package com.openhelp.dictionary.controller;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.country.CountryDto;
import com.openhelp.dictionary.dto.country.CountryFilterDto;
import com.openhelp.dictionary.service.CountryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@RestController
@AllArgsConstructor
public class CountryController {
    
    private final CountryService countryService;

    @PostMapping("/country")
    public ResponseEntity<ListDto<CountryDto>> list(@NotNull @RequestBody @Valid CountryFilterDto filter) {
        log.debug("Get Country list by filter: {}", filter);
        return ResponseEntity.ok(countryService.getList(filter));
    }

    @PostMapping("/country/{id}")
    public ResponseEntity<CountryDto> get(@NotNull @PathVariable(name = "id") Long countryId) {
        log.debug("Get Country by id: {}", countryId);
        return ResponseEntity.ok(countryService.findById(countryId));
    }

    @PostMapping("/country/create")
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid CountryDto country) {
        log.debug("Create country: {}", country);
        return ResponseEntity.ok(countryService.create(country));
    }

    @PostMapping("/country/{id}/update")
    public ResponseEntity<Long> create(@NotNull @PathVariable(name = "id") Long countryId,
                                       @NotNull @RequestBody @Valid CountryDto country) {
        log.debug("Update Country id: {}, {}", countryId, country);
        return ResponseEntity.ok(countryService.update(countryId, country));
    }

    @PostMapping("/country/{id}/delete")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long countryId) {
        log.debug("Delete Country id: {}", countryId);
        return ResponseEntity.ok(countryService.delete(countryId));
    }
}
