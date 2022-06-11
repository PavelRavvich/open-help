package com.openhelp.profile.controller;

import com.openhelp.profile.dto.access.AccessRequestDto;
import com.openhelp.profile.dto.access.AccessResponseDto;
import com.openhelp.profile.service.AccessService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<AccessResponseDto> getAccess(@NotNull @RequestBody AccessRequestDto req) {
        return ResponseEntity.ok(accessService.getAccesses(req));
    }
}
