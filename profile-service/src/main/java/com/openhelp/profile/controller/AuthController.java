package com.openhelp.profile.controller;

import com.openhelp.profile.dto.auth.AuthRequestDto;
import com.openhelp.profile.dto.auth.AuthResponseDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Pavel Ravvich.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<Long> registration(@NotNull @RequestBody @Valid SignUpRequestDto req) {
        return ResponseEntity.ok(authService.registration(req));
    }

    @GetMapping("/registration/activate/{activationCode}")
    public RedirectView activate(@NotNull @PathVariable(name = "activationCode") UUID activationCode) {
        return new RedirectView(authService.activate(activationCode));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@NotNull @RequestBody @Valid AuthRequestDto req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/checkToken")
    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    public ResponseEntity<AuthResponseDto> checkToken(@NotNull @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(authService.checkToken(token));
    }
}