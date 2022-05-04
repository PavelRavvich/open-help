package com.openhelp.story.mapper;

import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.model.Task;
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

    @Mapping(target = "storyId", ignore = true)
    TaskDto taskToTaskDto(Task task);

    @Mapping(target = "story", ignore = true)
    Task taskDtoToTask(TaskDto dto);
}
