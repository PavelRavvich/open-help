package com.openhelp.profile.controller;

import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.dto.role.RoleFilterDto;
import com.openhelp.profile.mapper.RoleMapper;
import com.openhelp.profile.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    private final RoleMapper roleMapper;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleDto>> list(@NotNull @RequestBody @Valid RoleFilterDto filter) {
        return ResponseEntity.ok(roleService.list(roleMapper.toRoleFilter(filter))
                .stream().map(roleMapper::roleToRoleDto)
                .collect(Collectors.toList()));
    }
}
