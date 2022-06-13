package com.openhelp.profile.controller;

import com.openhelp.profile.dto.access.UserAccessDto;
import com.openhelp.profile.service.AccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<UserAccessDto> getAccesses() {
        return ResponseEntity.ok(accessService.getAccesses());
    }
}
