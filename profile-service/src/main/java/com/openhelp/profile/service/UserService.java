package com.openhelp.profile.service;

import com.openhelp.profile.dto.ListDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.dto.user.UserDto;
import com.openhelp.profile.dto.user.UserFilterDto;
import com.openhelp.profile.model.User;

import java.util.UUID;

/**
 * @author Pavel Ravvich.
 */
public interface UserService {

    Long signUp(SignUpRequestDto sign);

    Long create(User user, boolean isEnabled);

    String activateUser(UUID activationCode);

    Long update(Long userId, SignUpRequestDto signUp);

    User getUserByUsername(String username);

    ListDto<UserDto> list(UserFilterDto filter);

    UserDto findById(Long userId);

    Long updatePassword(Long userId, String oldPassword, String newPassword);

    Long updateNickname(Long userId, String nickname);

    Long delete(Long userId);
}