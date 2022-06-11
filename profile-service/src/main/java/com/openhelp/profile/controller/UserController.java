package com.openhelp.profile.controller;

import com.openhelp.profile.dto.ListDto;
import com.openhelp.profile.dto.access.AccessResponseDto;
import com.openhelp.profile.dto.auth.SignUpRequestDto;
import com.openhelp.profile.dto.user.UserDto;
import com.openhelp.profile.dto.user.UserFilterDto;
import com.openhelp.profile.dto.user.UserItemDto;
import com.openhelp.profile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Pavel Ravvich.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ListDto<UserItemDto>> list(@NotNull @RequestBody @Valid UserFilterDto filter) {
        return ResponseEntity.ok(userService.list(filter));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> get(
            @NotNull @PathVariable(name = "id") Long id,
            @NotNull @RequestHeader(name = HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS) AccessResponseDto credentials) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid SignUpRequestDto req) {
        return ResponseEntity.ok(userService.create(req));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Long> update(@NotNull @PathVariable(name = "id") Long userId,
                                       @NotNull @RequestBody @Valid SignUpRequestDto req) {
        return ResponseEntity.ok(userService.update(userId, req));
    }

    @PostMapping("/{id}/update/password")
    public ResponseEntity<Long> updatePassword(@NotNull @PathVariable(name = "id") Long userId,
                                               @NotNull @RequestBody Map<String, String> req) {
        return ResponseEntity.ok(userService.updatePassword(userId, req.get("oldPassword"), req.get("newPassword")));
    }

    @PostMapping("/{id}/update/nickname")
    public ResponseEntity<Long> updateNickname(@NotNull @PathVariable(name = "id") Long userId,
                                               @NotNull @RequestBody Map<String, String> req) {
        return ResponseEntity.ok(userService.updateNickname(userId, req.get("nickname")));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }
}
