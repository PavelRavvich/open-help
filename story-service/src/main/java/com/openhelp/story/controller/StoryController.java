package com.openhelp.story.controller;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.story.StoryDto;
import com.openhelp.story.dto.story.StoryFilterDto;
import com.openhelp.story.service.StoryService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @GetMapping
    public ResponseEntity<ListDto<StoryDto>> list(@NotNull @RequestBody @Valid StoryFilterDto filter) {
        log.debug("Get Story list by filter: {}", filter);
        return ResponseEntity.ok(storyService.getList(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryDto> get(@NotNull @PathVariable(name = "id") Long storyId) {
        log.debug("Get Story by id: {}", storyId);
        return ResponseEntity.ok(storyService.findById(storyId));
    }

    @PostMapping
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid StoryDto story) {
        log.debug("Create Story: {}", story);
        return ResponseEntity.ok(storyService.create(story));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@NotNull @PathVariable(name = "id") Long id,
                                       @NotNull @RequestBody @Valid StoryDto story) {
        log.debug("Update story id: {}, {}", id, story);
        return ResponseEntity.ok(storyService.update(id, story));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long id) {
        log.debug("Delete Story by id: {}", id);
        return ResponseEntity.ok(storyService.delete(id));
    }
}
