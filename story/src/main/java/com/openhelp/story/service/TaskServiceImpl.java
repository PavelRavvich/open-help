package com.openhelp.story.service;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.task.TaskDto;
import com.openhelp.story.dto.task.TaskFilterDto;
import com.openhelp.story.mapper.TaskMapper;
import com.openhelp.story.model.Task;
import com.openhelp.story.repository.TaskRepository;
import com.openhelp.story.repository.filter.TaskFilter;
import com.openhelp.story.validation.NoSuchTaskException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    @Override
    public ListDto<TaskDto> getList(@NotNull TaskFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(), filterDto.getPageSize());
        TaskFilter filter = taskMapper.taskFilterToTaskFilterDto(filterDto);
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

    @Override
    public TaskDto findById(@NotNull Long id) {
        TaskDto task = taskMapper.taskToTaskDto(
                taskRepository.findById(id)
                        .orElseThrow(NoSuchTaskException::new));
        log.info("Get task {}", task);
        return task;
    }

    @Override
    public Long create(@NotNull TaskDto taskDto) {
        Long id = taskRepository.save(taskMapper.taskDtoToTask(taskDto)).getId();
        log.info("Create task id: {}, {}", id, taskDto);
        return id;
    }

    @Override
    public Long update(@NotNull Long id, @NotNull TaskDto taskDto) {
        taskDto.setId(id);
        taskRepository.save(taskMapper.taskDtoToTask(taskDto));
        log.info("Update task id: {}, {}", id, taskDto);
        return id;
    }

    @Override
    public Long delete(@NotNull Long id) {
        taskRepository.updateDeletedAtById(id, new Timestamp(System.currentTimeMillis()));
        log.info("Delete task id: {}", id);
        return id;
    }
}
