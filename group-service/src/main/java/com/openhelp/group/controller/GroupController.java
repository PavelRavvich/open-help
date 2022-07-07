package com.openhelp.group.controller;

import com.openhelp.group.dto.ListDto;
import com.openhelp.group.dto.group.GroupDto;
import com.openhelp.group.dto.group.GroupFilterDto;
import com.openhelp.group.dto.group.GroupItemDto;
import com.openhelp.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@Slf4j
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<ListDto<GroupItemDto>> list(GroupFilterDto filter) {
        return ResponseEntity.ok(groupService.list(filter));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GroupDto> get(@NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(groupService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid GroupDto group) {
        return ResponseEntity.ok(groupService.create(group));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@NotNull @PathVariable(name = "id") Long id,
                                       @NotNull @RequestBody @Valid GroupDto group) {
        return ResponseEntity.ok(groupService.update(id, group));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(groupService.delete(id));
    }
}
