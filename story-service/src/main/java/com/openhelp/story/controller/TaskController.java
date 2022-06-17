package com.openhelp.story.controller;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.dto.task.TaskFilterDto;
import com.openhelp.story.service.TaskService;
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
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{storyId}/tasks")
    public ResponseEntity<ListDto<TaskDto>> list(@NotNull @PathVariable(name = "storyId") Long storyId,
                                                 @NotNull @RequestBody @Valid TaskFilterDto filter) {
        log.debug("Get task list by filter: {}", filter);
        return ResponseEntity.ok(taskService.getList(storyId, filter));
    }

    @GetMapping("/{storyId}/tasks/{taskId}")
    public ResponseEntity<TaskDto> get(@NotNull @PathVariable(name = "storyId") Long storyId,
                                       @NotNull @PathVariable(name = "taskId") Long taskId) {
        log.debug("Get task by id: {}", taskId);
        return ResponseEntity.ok(taskService.findById(storyId, taskId));
    }

    @PostMapping("/{storyId}/tasks")
    public ResponseEntity<Long> create(@NotNull @PathVariable(name = "storyId") Long storyId,
                                       @NotNull @RequestBody @Valid TaskDto task) {
        log.debug("Create task: {}", task);
        return ResponseEntity.ok(taskService.create(storyId, task));
    }

    @PutMapping("/{storyId}/tasks/{taskId}")
    public ResponseEntity<Long> create(@NotNull @PathVariable(name = "storyId") Long storyId,
                                       @NotNull @PathVariable(name = "taskId") Long taskId,
                                       @NotNull @RequestBody @Valid TaskDto task) {
        log.debug("Update task id: {}, {}", taskId, task);
        return ResponseEntity.ok(taskService.update(storyId, taskId, task));
    }

    @DeleteMapping("/{storyId}/tasks/{taskId}")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "storyId") Long storyId,
                                       @NotNull @PathVariable(name = "taskId") Long taskId) {
        log.debug("Delete task id: {}", taskId);
        return ResponseEntity.ok(taskService.delete(storyId, taskId));
    }
}
