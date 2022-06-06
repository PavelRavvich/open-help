package com.openhelp.profile.controller;

import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.dto.role.RoleFilterDto;
import com.openhelp.profile.dto.role.RoleRequestDto;
import com.openhelp.profile.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleDto>> list(@NotNull @RequestBody @Valid RoleFilterDto filter) {
        return ResponseEntity.ok(roleService.list(filter));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> get(@NotNull @PathVariable(name = "id") Long roleId) {
        return ResponseEntity.ok(roleService.findById(roleId));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid RoleRequestDto req) {
        return ResponseEntity.ok(roleService.create(req));
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> update(@NotNull @PathVariable(name = "id") Long id,
                                       @NotNull @RequestBody @Valid RoleRequestDto req) {
        return ResponseEntity.ok(roleService.update(id, req));
    }
}
