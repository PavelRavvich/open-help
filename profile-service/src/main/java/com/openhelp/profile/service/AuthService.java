package com.openhelp.profile.service;

import com.openhelp.profile.dto.auth.AuthRequestDto;
import com.openhelp.profile.dto.auth.AuthResponseDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;

import java.util.UUID;

/**
 * @author Pavel Ravvich.
 */
public interface AuthService {

    Long registration(SignUpRequestDto signup);

    String activate(UUID activationCode);

    AuthResponseDto login(AuthRequestDto auth);

    AuthResponseDto checkToken(String token);
}
