package com.openhelp.profile.controller;

import com.openhelp.profile.dto.access.AccessesDto;
import com.openhelp.profile.enums.EntityType;
import com.openhelp.profile.service.AccessService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Ravvich.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/accesses")
public class AccessController {

    private final AccessService accessService;

    @GetMapping("/{entityType}")
    public ResponseEntity<AccessesDto> getAccesses(
            @NotNull @PathVariable(name = "entityType") EntityType entityType) {
        return ResponseEntity.ok(accessService.getAccesses(entityType));
    }
}
