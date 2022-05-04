package com.openhelp.story.controller;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.dto.task.TaskFilterDto;
import com.openhelp.story.service.TaskService;
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
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<ListDto<TaskDto>> list(@NotNull @RequestBody @Valid TaskFilterDto filter) {
        log.debug("Get task list by filter: {}", filter);
        return ResponseEntity.ok(taskService.getList(filter));
    }

    @PostMapping("/task/{id}")
    public ResponseEntity<TaskDto> get(@NotNull @PathVariable(name = "id") Long taskId) {
        log.debug("Get task by id: {}", taskId);
        return ResponseEntity.ok(taskService.findById(taskId));
    }

    @PostMapping("/task/create")
    public ResponseEntity<Long> create(@NotNull @RequestBody @Valid TaskDto task) {
        log.debug("Create task: {}", task);
        return ResponseEntity.ok(taskService.create(task));
    }

    @PostMapping("/task/{id}/update")
    public ResponseEntity<Long> create(@NotNull @PathVariable(name = "id") Long taskId,
                                       @NotNull @RequestBody @Valid TaskDto task) {
        log.debug("Update task id: {}, {}", taskId, task);
        return ResponseEntity.ok(taskService.update(taskId, task));
    }

    @PostMapping("/task/{id}/delete")
    public ResponseEntity<Long> delete(@NotNull @PathVariable(name = "id") Long taskId) {
        log.debug("Delete task id: {}", taskId);
        return ResponseEntity.ok(taskService.delete(taskId));
    }
}
