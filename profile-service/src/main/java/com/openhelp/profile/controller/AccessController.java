package com.openhelp.profile.controller;

import com.openhelp.profile.service.AccessService;
import com.openhelp.profile.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.openhelp.profile.utils.SecurityUtils.UserAccessDto;

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
