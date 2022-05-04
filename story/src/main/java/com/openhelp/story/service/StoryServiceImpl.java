package com.openhelp.story.service;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.story.StoryDto;
import com.openhelp.story.dto.story.StoryFilterDto;
import com.openhelp.story.mapper.StoryMapper;
import com.openhelp.story.model.Story;
import com.openhelp.story.repository.StoryRepository;
import com.openhelp.story.repository.StoryRepository.StorySpecification;
import com.openhelp.story.repository.filter.StoryFilter;
import com.openhelp.story.validation.NoSuchStoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {

    private final StoryMapper storyMapper;

    private final StoryRepository storyRepository;

    @Override
    public ListDto<StoryDto> getList(@NotNull StoryFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(), filterDto.getPageSize());
        StoryFilter filter = storyMapper.storyFilterDtoToStoryFilter(filterDto);
        Page<Story> page = storyRepository.findAll(
                new StorySpecification(filter), pageable);
        List<StoryDto> items = page
                .map(storyMapper::storyToStoryDto)
                .getContent();

        log.info("Get story list {}", items);
        return ListDto.<StoryDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
    }

    @Override
    public StoryDto findById(@NotNull Long id) {
        StoryDto story = storyMapper.storyToStoryDto(
                storyRepository.findById(id)
                        .orElseThrow(NoSuchStoryException::new));
        log.info("Get story {}", story);
        return story;
    }

    @Override
    public Long create(@NotNull StoryDto dto) {
        Long id = storyRepository.save(storyMapper.storyDtoToStory(dto)).getId();
        log.info("Create story id: {}, {}", id, dto);
        return id;
    }

    @Override
    public Long update(@NotNull Long id, @NotNull StoryDto dto) {
        dto.setId(id);
        storyRepository.save(storyMapper.storyDtoToStory(dto));
        log.info("Update story id: {}, {}", id, dto);
        return id;
    }

    @Override
    public Long delete(@NotNull Long id) {
        storyRepository.updateDeletedAtById(id, new Timestamp(System.currentTimeMillis()));
        log.info("Delete story id: {}", id);
        return id;
    }
}
