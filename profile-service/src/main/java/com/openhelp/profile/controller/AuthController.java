package com.openhelp.profile.controller;

import com.openhelp.profile.config.jwt.JwtTokenProvider;
import com.openhelp.profile.config.jwt.JwtUser;
import com.openhelp.profile.dto.auth.AuthRequestDto;
import com.openhelp.profile.dto.auth.AuthResponseDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.enums.RoleType;
import com.openhelp.profile.mapper.AuthMapper;
import com.openhelp.profile.model.User;
import com.openhelp.profile.service.RoleService;
import com.openhelp.profile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author Pavel Ravvich.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RoleService roleService;

    private final AuthMapper authMapper;

    @PostMapping("/registration")
    public ResponseEntity<Long> registration(@NotNull @RequestBody @Valid SignUpRequestDto req) {
        req.setIsEnabled(false);
        req.setRoleIds(List.of(roleService.findBySystemName(RoleType.USER.getType()).getId()));
        return ResponseEntity.ok(userService.create(authMapper.signUpRequestDtoToUser(req), false));
    }

    @GetMapping("/registration/activate/{activationCode}")
    public RedirectView activate(@NotNull @PathVariable(name = "activationCode") UUID activationCode) {
        return new RedirectView(userService.activateUser(activationCode));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@NotNull @RequestBody @Valid AuthRequestDto req) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = jwtTokenProvider.createToken(req.getUsername());
        User user = ((JwtUser) authentication.getPrincipal()).getUser();
        return ResponseEntity.ok(authMapper.toAuthResponseDto(token, user));
    }

    @PostMapping("/checkToken")
    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    public ResponseEntity<AuthResponseDto> checkToken(@NotNull @RequestHeader(name = "Authorization") String token) {
        String accessToken = token.replace("Bearer ", "");
        return ResponseEntity.ok(authMapper.toAuthResponseDto(accessToken, userService.getSecurityContextUser()));
    }

}