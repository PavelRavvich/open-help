package com.openhelp.story.service;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.dto.task.TaskFilterDto;

/**
 * @author Pavel Ravvich.
 */
public interface TaskService {

    ListDto<TaskDto> getList(TaskFilterDto filter);

    TaskDto findById(Long id);

    Long create(TaskDto story);

    Long update(Long id, TaskDto storyDto);

    Long delete(Long id);
}
