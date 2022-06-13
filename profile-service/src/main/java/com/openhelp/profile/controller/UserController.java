package com.openhelp.profile.controller;

import com.openhelp.profile.dto.ListDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.dto.user.UserDto;
import com.openhelp.profile.dto.user.UserFilterDto;
import com.openhelp.profile.dto.user.UserItemDto;
import com.openhelp.profile.dto.user.UserNicknameUpdateRequest;
import com.openhelp.profile.dto.user.UserPasswordUpdateRequest;
import com.openhelp.profile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Pavel Ravvich.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ListDto<UserItemDto>> list(@NotNull @RequestBody @Valid UserFilterDto filter) {
        return ResponseEntity.ok(userService.list(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid SignUpRequestDto req) {
        return ResponseEntity.ok(userService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@NotNull @PathVariable(name = "id") Long userId,
                                       @NotNull @RequestBody @Valid SignUpRequestDto req) {
        return ResponseEntity.ok(userService.update(userId, req));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Long> updatePassword(@NotNull @PathVariable(name = "id") Long userId,
                                               @NotNull @RequestBody UserPasswordUpdateRequest req) {
        return ResponseEntity.ok(userService.updatePassword(userId, req.getOldPassword(), req.getNewPassword()));
    }

    @PatchMapping("/{id}/nickname")
    public ResponseEntity<Long> updateNickname(@NotNull @PathVariable(name = "id") Long userId,
                                               @NotNull @RequestBody @Valid UserNicknameUpdateRequest req) {
        return ResponseEntity.ok(userService.updateNickname(userId, req.getNickname()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }
}
