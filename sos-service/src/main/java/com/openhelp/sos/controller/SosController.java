package com.openhelp.sos.controller;

import com.openhelp.sos.dto.ListDto;
import com.openhelp.sos.dto.sos.SosDto;
import com.openhelp.sos.dto.sos.SosFilterDto;
import com.openhelp.sos.service.SosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SosController {

    private final SosService sosService;

    @GetMapping
    public ResponseEntity<ListDto<SosDto>> list(SosFilterDto filter) {
        return ResponseEntity.ok(sosService.list(filter));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SosDto> get(@NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(sosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid SosDto sos) {
        return ResponseEntity.ok(sosService.create(sos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@NotNull @PathVariable(name = "id") Long id,
                                       @NotNull @RequestBody @Valid SosDto sos) {
        return ResponseEntity.ok(sosService.update(id, sos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(sosService.delete(id));
    }
}
