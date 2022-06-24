package com.openhelp.profile.service;

import com.openhelp.profile.dto.ListDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.dto.user.UserDto;
import com.openhelp.profile.dto.user.UserFilterDto;
import com.openhelp.profile.dto.user.UserItemDto;
import com.openhelp.profile.enums.EntityType;
import com.openhelp.profile.enums.OperationType;
import com.openhelp.profile.enums.RoleType;
import com.openhelp.profile.mapper.AuthMapper;
import com.openhelp.profile.mapper.UserMapper;
import com.openhelp.profile.model.User;
import com.openhelp.profile.repository.UserRepository;
import com.openhelp.profile.repository.filter.UserFilter;
import com.openhelp.profile.utils.SecurityUtils;
import com.openhelp.profile.utils.Utils;
import com.openhelp.profile.validation.AccessDeniedException;
import com.openhelp.profile.validation.PasswordChangeException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

import static com.openhelp.profile.repository.UserRepository.UserSpecification;

/**
 * @author Pavel Ravvich.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MailService mailService;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final AuthMapper authMapper;

    @Override
    public Long create(@NotNull SignUpRequestDto signUp) {
        User user = authMapper.signUpRequestDtoToUser(signUp);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setActivationCode(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Long id = userRepository.save(user).getId();
        if (Objects.isNull(user.getIsEnabled()) || !user.getIsEnabled()) {
            mailService.sendVerification(user);
        }
        return id;
    }

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
    public void updateIsEnabledByActivationCode(@NotNull UUID activationCode) {
        userRepository.updateIsEnabledByActivationCode(true, activationCode);
    }

    @Override
    public Long update(@NotNull Long userId, @NotNull SignUpRequestDto sign) {
        User candidate = authMapper.signUpRequestDtoToUser(sign);
        User user = userRepository.findDistinctById(userId).orElseThrow(NoSuchElementException::new);
        user.setIsEnabled(candidate.getIsEnabled());
        user.setCredentialsNonExpired(candidate.getIsEnabled());
        user.setAccountNonExpired(candidate.getIsEnabled());
        user.setAccountNonLocked(candidate.getIsEnabled());
        user.setRoles(candidate.getRoles());
        user.setNickname(candidate.getNickname());
        return userRepository.save(user).getId();
    }

    @Override
    public User findByUsername(@NotNull String username) {
        return userRepository
                .findDistinctByUsername(username)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public ListDto<UserItemDto> list(@NotNull UserFilterDto filterDto) {
        if (!SecurityUtils.is(OperationType.READ_ANY, EntityType.USER)) {
            throw new AccessDeniedException();
        }
        UserFilter filter = userMapper.toUserFilter(filterDto);
        Pageable pagination = PageRequest.of(filterDto.getPageNumber(),
                filterDto.getPageSize(), Utils.getSort(filterDto));
        UserSpecification specification = new UserSpecification(filter);
        Specification<User> where = Specification.where(specification);
        Page<User> page = userRepository.findAll(where, pagination);
        return ListDto.<UserItemDto>builder()
                .total(page.getTotalElements())
                .items(page.map(userMapper::userToUserItemDto).getContent())
                .build();
    }

    @Override
    public UserDto findById(@NotNull Long userId) {
        if (!SecurityUtils.is(OperationType.READ_ANY, EntityType.USER)
                && !SecurityUtils.getUserAccess().getUserId().equals(userId)) {
            throw new AccessDeniedException();
        }
        User user = userRepository.findDistinctById(userId).orElseThrow();
        return userMapper.userToUserDto(user);
    }

    @Override
    public Long updatePassword(@NotNull Long userId,
                               @NotNull String oldPassword,
                               @NotNull String newPassword) {
        if (!SecurityUtils.is(OperationType.UPDATE_ANY, EntityType.USER)
                && !SecurityUtils.getUserAccess().getUserId().equals(userId)) {
            throw new AccessDeniedException();
        }
        User user = userRepository
                .findDistinctById(userId)
                .orElseThrow(NoSuchElementException::new);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            userRepository.updatePasswordById(userId, passwordEncoder.encode(newPassword));
        } else {
            throw new PasswordChangeException();
        }
        return userId;
    }

    @Override
    public Long updateNickname(@NotNull Long userId, @NotNull String nickname) {
        if (!SecurityUtils.is(OperationType.UPDATE_ANY, EntityType.USER)
                && !SecurityUtils.getUserAccess().getUserId().equals(userId)) {
            throw new AccessDeniedException();
        }
        userRepository.updateNicknameById(userId, nickname);
        return userId;
    }

    @Override
    public Long delete(@NotNull Long userId) {
        if (!SecurityUtils.is(OperationType.DELETE_ANY, EntityType.USER)
                && !SecurityUtils.getUserAccess().getUserId().equals(userId)) {
            throw new AccessDeniedException();
        }
        userRepository.deleteById(userId);
        return userId;
    }
}