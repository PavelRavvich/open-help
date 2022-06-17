package com.openhelp.story.mapper;

import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.dto.task.TaskFilterDto;
import com.openhelp.story.model.Task;
import com.openhelp.story.repository.filter.TaskFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class})
public interface TaskMapper {

    @Mapping(target = "storyId", source = "story.id")
    TaskDto taskToTaskDto(Task task);

    @Mapping(target = "story.id", source = "storyId")
    Task taskDtoToTask(TaskDto dto);

    @Mapping(target = "story.id", source = "storyId")
    TaskFilter taskFilterToTaskFilterDto(Long storyId, TaskFilterDto dto);
}
