package com.openhelp.story.controller;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.story.StoryDto;
import com.openhelp.story.dto.story.StoryFilterDto;
import com.openhelp.story.service.StoryService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @PostMapping
    public ResponseEntity<ListDto<StoryDto>> list(@NotNull @RequestBody @Valid StoryFilterDto filter) {
        log.debug("Get story list by filter: {}", filter);
        return ResponseEntity.ok(storyService.getList(filter));
    }

    @PostMapping("/{id}")
    public ResponseEntity<StoryDto> get(@NotNull @PathVariable(name = "id") Long storyId) {
        log.debug("Get story by id: {}", storyId);
        return ResponseEntity.ok(storyService.findById(storyId));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid StoryDto story) {
        log.debug("Create story: {}", story);
        return ResponseEntity.ok(storyService.create(story));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Long> create(@NotNull @PathVariable(name = "id") Long storyId,
                                       @NotNull @RequestBody @Valid StoryDto story) {
        log.debug("Update story id: {}, {}", storyId, story);
        return ResponseEntity.ok(storyService.update(storyId, story));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long storyId) {
        log.debug("Delete story id: {}", storyId);
        return ResponseEntity.ok(storyService.delete(storyId));
    }
}
