package com.openhelp.dictionary.controller;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.tag.TagDto;
import com.openhelp.dictionary.dto.tag.TagFilterDto;
import com.openhelp.dictionary.service.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@RestController
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/tag")
    public ResponseEntity<ListDto<TagDto>> list(@NotNull @RequestBody @Valid TagFilterDto filter) {
        log.debug("Get Tag list by filter: {}", filter);
        return ResponseEntity.ok(tagService.getList(filter));
    }

    @PostMapping("/tag/{id}")
    public ResponseEntity<TagDto> get(@NotNull @PathVariable(name = "id") Long tagId) {
        log.debug("Get Tag by id: {}", tagId);
        return ResponseEntity.ok(tagService.findById(tagId));
    }

    @PostMapping("/tag/create")
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid TagDto tag) {
        log.debug("Create Tag: {}", tag);
        return ResponseEntity.ok(tagService.create(tag));
    }

    @PostMapping("/tag/{id}/update")
    public ResponseEntity<Long> create(@NotNull @PathVariable(name = "id") Long tagId,
                                       @NotNull @RequestBody @Valid TagDto tag) {
        log.debug("Update Tag id: {}, {}", tagId, tag);
        return ResponseEntity.ok(tagService.update(tagId, tag));
    }

    @PostMapping("/tag/{id}/delete")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long tagId) {
        log.debug("Delete Tag id: {}", tagId);
        return ResponseEntity.ok(tagService.delete(tagId));
    }
}
