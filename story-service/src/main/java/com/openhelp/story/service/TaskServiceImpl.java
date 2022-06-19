package com.openhelp.story.service;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.dto.task.TaskFilterDto;
import com.openhelp.story.enums.EntityType;
import com.openhelp.story.enums.OperationType;
import com.openhelp.story.mapper.TaskMapper;
import com.openhelp.story.model.Story;
import com.openhelp.story.model.Task;
import com.openhelp.story.repository.StoryRepository;
import com.openhelp.story.repository.TaskRepository;
import com.openhelp.story.repository.filter.TaskFilter;
import com.openhelp.story.utils.SecurityUtils;
import com.openhelp.story.validation.AccessDeniedException;
import com.openhelp.story.validation.NoSuchStoryException;
import com.openhelp.story.validation.NoSuchTaskException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.openhelp.story.repository.TaskRepository.TaskSpecification;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    private final StoryRepository storyRepository;

    @Override
    public ListDto<TaskDto> getList(@NotNull Long storyId, @NotNull TaskFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(), filterDto.getPageSize());
        TaskFilter filter = checkAccess(taskMapper.taskFilterToTaskFilterDto(storyId, filterDto));
        Page<Task> page = taskRepository.findAll(
                new TaskSpecification(filter), pageable);
        List<TaskDto> items = page
                .map(taskMapper::taskToTaskDto)
                .getContent();
        log.info("Get task list {}", items);
        return ListDto.<TaskDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
    }

    private TaskFilter checkAccess(@NotNull TaskFilter filter) {
        boolean isReadAny = SecurityUtils.is(OperationType.READ_ANY, EntityType.STORY);
        boolean isReadOwn = SecurityUtils.is(OperationType.READ_OWN, EntityType.STORY);
        if (!isReadAny && isReadOwn) {
            Long userId = SecurityUtils.getUserAccess().getUserId();
            filter.setStory(Story.builder().userId(userId).build());
        } else if (!isReadOwn && !isReadAny) {
            throw new AccessDeniedException();
        }
        return filter;
    }

    @Override
    public TaskDto findById(@NotNull Long storyId, @NotNull Long taskId) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(NoSuchStoryException::new);
        SecurityUtils.checkReadAccess(story.getUserId(), EntityType.STORY);
        TaskDto task = taskMapper.taskToTaskDto(
                taskRepository.findById(storyId)
                        .orElseThrow(NoSuchTaskException::new));
        log.info("Get Task by id: {} {}", storyId, task);
        return task;
    }

    @Override
    public Long create(@NotNull Long storyId, @NotNull TaskDto taskDto) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(NoSuchStoryException::new);
        SecurityUtils.checkUpdateAccess(story.getUserId(), EntityType.STORY);
        Long id = taskRepository.save(taskMapper.taskDtoToTask(taskDto)).getId();
        log.info("Create task id: {}, {}", id, taskDto);
        return id;
    }

    @Override
    public Long update(@NotNull Long storyId, @NotNull Long taskId, @NotNull TaskDto taskDto) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(NoSuchStoryException::new);
        SecurityUtils.checkUpdateAccess(story.getUserId(), EntityType.STORY);
        if (!taskRepository.existsByIdAndStory(taskId, story)) {
            throw new NoSuchTaskException();
        }
        Task task = taskMapper.taskDtoToTask(taskDto);
        task.setId(taskId);
        task.setStory(story);
        taskRepository.save(task);
        log.info("Update Task by id: {}, and storyId: {}, {}", taskId, storyId, taskDto);
        return taskId;
    }

    @Override
    public Long delete(@NotNull Long storyId, @NotNull Long taskId) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(NoSuchStoryException::new);
        SecurityUtils.checkDeleteAccess(story.getUserId(), EntityType.STORY);
        taskRepository.deleteById(taskId);
        log.info("Delete Task by id: {}", taskId);
        return taskId;
    }
}
