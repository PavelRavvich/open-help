package com.openhelp.profile.service;

import com.openhelp.profile.dto.ListDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.dto.user.UserDto;
import com.openhelp.profile.dto.user.UserFilterDto;
import com.openhelp.profile.dto.user.UserItemDto;
import com.openhelp.profile.model.User;

import java.util.UUID;

/**
 * @author Pavel Ravvich.
 */
public interface UserService {

    UserDto findById(Long userId);

    User findByUsername(String username);

    ListDto<UserItemDto> list(UserFilterDto filter);

    Long create(SignUpRequestDto sign);

    Long create(User user, boolean isEnabled);

    Long update(Long userId, SignUpRequestDto signUp);

    void updateIsEnabledByActivationCode(UUID activationCode);

    Long updatePassword(Long userId, String oldPassword, String newPassword);

    Long updateNickname(Long userId, String nickname);

    Long delete(Long userId);
}