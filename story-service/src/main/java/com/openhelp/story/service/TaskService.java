package com.openhelp.story.service;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.dto.task.TaskFilterDto;

/**
 * @author Pavel Ravvich.
 */
public interface TaskService {

    ListDto<TaskDto> getList(Long storyId, TaskFilterDto filter);

    TaskDto findById(Long storyId, Long taskId);

    Long create(Long storyId, TaskDto story);

    Long update(Long storyId, Long taskId, TaskDto storyDto);

    Long delete(Long storyId, Long taskId);
}
