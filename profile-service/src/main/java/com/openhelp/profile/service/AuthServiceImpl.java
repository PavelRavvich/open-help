package com.openhelp.profile.service;

import com.openhelp.profile.config.jwt.JwtTokenProvider;
import com.openhelp.profile.config.jwt.JwtUser;
import com.openhelp.profile.dto.auth.AuthRequestDto;
import com.openhelp.profile.dto.auth.AuthResponseDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.enums.RoleType;
import com.openhelp.profile.mapper.AuthMapper;
import com.openhelp.profile.model.User;
import com.openhelp.profile.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RoleService roleService;

    private final AuthMapper authMapper;

    @Override
    public Long registration(@NotNull SignUpRequestDto signup) {
        RoleDto role = roleService.findBySystemName(RoleType.USER.getType());
        signup.setIsEnabled(false);
        signup.setRoleIds(List.of(role.getId()));
        User user = authMapper.signUpRequestDtoToUser(signup);
        return userService.create(user, false);
    }

    @Override
    public String activate(@NotNull UUID activationCode) {
        return userService.activateUser(activationCode);
    }

    @Override
    public AuthResponseDto login(@NotNull AuthRequestDto auth) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        String token = jwtTokenProvider.createToken(auth.getUsername());
        User user = ((JwtUser) authentication.getPrincipal()).getUser();
        return authMapper.toAuthResponseDto(token, user);
    }

    @Override
    public AuthResponseDto checkToken(@NotNull String token) {
        String accessToken = token.replace("Bearer ", "");
        User user = SecurityUtils.getSecurityContextUser();
        return authMapper.toAuthResponseDto(accessToken, user);
    }
}
