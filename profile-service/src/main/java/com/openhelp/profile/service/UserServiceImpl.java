package com.openhelp.profile.service;

import com.openhelp.profile.enums.RoleType;
import com.openhelp.profile.model.User;
import com.openhelp.profile.repository.UserRepository;
import com.openhelp.profile.repository.filter.UserFilter;
import com.openhelp.profile.validation.AccessDeniedException;
import com.openhelp.profile.validation.PasswordChangeException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * @author Pavel Ravvich.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${activation.redirect.url}")
    private String activationRedirectUrl;

    private final MailService mailService;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Long create(@NotNull User user, boolean isEnabled) {
        user.setIsEnabled(isEnabled);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setActivationCode(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Long id = userRepository.save(user).getId();
        mailService.sendVerification(user);
        return id;
    }

    @Override
    public String activateUser(@NotNull UUID activationCode) {
        userRepository.updateIsEnabledByActivationCode(true, activationCode);
        return activationRedirectUrl;
    }

    @Override
    public Long update(@NotNull Long userId, @NotNull User candidate) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.setIsEnabled(candidate.getIsEnabled());
        user.setCredentialsNonExpired(candidate.getIsEnabled());
        user.setAccountNonExpired(candidate.getIsEnabled());
        user.setAccountNonLocked(candidate.getIsEnabled());
        user.setRoles(candidate.getRoles());
        user.setNickname(candidate.getNickname());
        return userRepository.save(user).getId();
    }

    @Override
    public User getUserByUsername(@NotNull String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> list(@NotNull Pageable pagination, @NotNull UserFilter filter) {
        UserRepository.UserSpecification specification = new UserRepository.UserSpecification(filter);
        return userRepository.findAll(where(specification), pagination);
    }

    @Override
    public User findById(@NotNull Long userId) {
        if (!anyMatchCredentials(RoleType.ADMIN) && !getSecurityContextUserId().equals(userId)) {
            throw new AccessDeniedException();
        }
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public Long updatePassword(@NotNull Long userId, @NotNull String oldPassword, @NotNull String newPassword) {
        if (!userId.equals(getSecurityContextUserId()) && !anyMatchCredentials(RoleType.ADMIN)) {
            throw new AccessDeniedException();
        }

        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            userRepository.updatePasswordById(userId, passwordEncoder.encode(newPassword));
        } else {
            throw new PasswordChangeException();
        }

        return userId;
    }

    @Override
    public Long updateNickname(@NotNull Long userId, @NotNull String nickname) {
        if (!userId.equals(getSecurityContextUserId()) && !anyMatchCredentials(RoleType.ADMIN)) {
            throw new AccessDeniedException();
        }
        userRepository.updateNicknameById(userId, nickname);
        return userId;
    }

    @Override
    public Long delete(@NotNull Long userId) {
        userRepository.deleteById(userId);
        return userId;
    }
}