package com.openhelp.dictionary.controller;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.city.CityDto;
import com.openhelp.dictionary.dto.city.CityFilterDto;
import com.openhelp.dictionary.service.CityService;
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
public class CityController {
    
    private final CityService cityService;

    @PostMapping("/city")
    public ResponseEntity<ListDto<CityDto>> list(@NotNull @RequestBody @Valid CityFilterDto filter) {
        log.debug("Get City list by filter: {}", filter);
        return ResponseEntity.ok(cityService.getList(filter));
    }

    @PostMapping("/city/{id}")
    public ResponseEntity<CityDto> get(@NotNull @PathVariable(name = "id") Long cityId) {
        log.debug("Get City by id: {}", cityId);
        return ResponseEntity.ok(cityService.findById(cityId));
    }

    @PostMapping("/city/create")
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid CityDto city) {
        log.debug("Create City: {}", city);
        return ResponseEntity.ok(cityService.create(city));
    }

    @PostMapping("/city/{id}/update")
    public ResponseEntity<Long> create(@NotNull @PathVariable(name = "id") Long cityId,
                                       @NotNull @RequestBody @Valid CityDto city) {
        log.debug("Update City id: {}, {}", cityId, city);
        return ResponseEntity.ok(cityService.update(cityId, city));
    }

    @PostMapping("/city/{id}/delete")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long cityId) {
        log.debug("Delete City id: {}", cityId);
        return ResponseEntity.ok(cityService.delete(cityId));
    }
}
