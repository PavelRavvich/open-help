package com.openhelp.story.service;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.story.StoryDto;
import com.openhelp.story.dto.story.StoryFilterDto;
import com.openhelp.story.enums.EntityType;
import com.openhelp.story.enums.OperationType;
import com.openhelp.story.mapper.StoryMapper;
import com.openhelp.story.model.Story;
import com.openhelp.story.repository.StoryRepository;
import com.openhelp.story.repository.StoryRepository.StorySpecification;
import com.openhelp.story.repository.filter.StoryFilter;
import com.openhelp.story.utils.SecurityUtils;
import com.openhelp.story.utils.Utils;
import com.openhelp.story.validation.AccessDeniedException;
import com.openhelp.story.validation.NoSuchStoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(),
                filterDto.getPageSize(), Utils.getSort(filterDto));
        StoryFilter filter = storyMapper.storyFilterDtoToStoryFilter(filterDto);
        setFilteringAccessLevel(filter);
        Page<Story> page = storyRepository.findAll(
                new StorySpecification(filter), pageable);
        List<StoryDto> items = page
                .map(storyMapper::storyToStoryDto)
                .getContent();
        log.info("Get Story list {}", items);
        return ListDto.<StoryDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
    }

    private void setFilteringAccessLevel(@NotNull StoryFilter filter) {
        boolean isReadAnyStory = SecurityUtils.is(EntityType.STORY, OperationType.READ_ANY);
        boolean isReadOwnStory = SecurityUtils.is(EntityType.STORY, OperationType.READ_OWN);
        if (!isReadOwnStory && !isReadAnyStory) {
            throw new AccessDeniedException();
        } else if (isReadOwnStory && !isReadAnyStory) {
            filter.setUserId(SecurityUtils.getUserAccess().getUserId());
        }
    }

    @Override
    public StoryDto findById(@NotNull Long id) {
        StoryDto story = storyMapper.storyToStoryDto(
                storyRepository.findById(id)
                        .orElseThrow(NoSuchStoryException::new));
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(story.getUserId());
        boolean isReadOwnStory = SecurityUtils.is(EntityType.STORY, OperationType.READ_OWN);
        boolean isReadAnyStory = SecurityUtils.is(EntityType.STORY, OperationType.READ_ANY);
        if (!isReadAnyStory && !(isOwn && isReadOwnStory)) {
            throw new AccessDeniedException();
        }
        log.info("Get Story {}", story);
        return story;
    }

    @Override
    public Long create(@NotNull StoryDto storyDto) {
        if (!SecurityUtils.is(EntityType.STORY, OperationType.CREATE)) {
            throw new AccessDeniedException();
        }
        Long id = storyRepository.save(storyMapper.storyDtoToStory(storyDto)).getId();
        log.info("Create Story id: {}, {}", id, storyDto);
        return id;
    }

    @Override
    @Transactional
    public Long update(@NotNull Long id, @NotNull StoryDto dto) {
        Story story = storyRepository.findById(id).orElseThrow(NoSuchStoryException::new);
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(story.getUserId());
        boolean isUpdateOwnStory = SecurityUtils.is(EntityType.STORY, OperationType.UPDATE_OWN);
        boolean isUpdateAnyStory = SecurityUtils.is(EntityType.STORY, OperationType.UPDATE_ANY);
        if (!isUpdateAnyStory && !(isOwn && isUpdateOwnStory)) {
            throw new AccessDeniedException();
        }
        dto.setId(id);
        storyRepository.save(storyMapper.storyDtoToStory(dto));
        log.info("Update Story: {}", dto);
        return id;
    }

    @Override
    @Transactional
    public Long delete(@NotNull Long id) {
        Story story = storyRepository.findById(id).orElseThrow(NoSuchStoryException::new);
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(story.getUserId());
        boolean isDeleteOwnStory = SecurityUtils.is(EntityType.STORY, OperationType.DELETE_OWN);
        boolean isDeleteAnyStory = SecurityUtils.is(EntityType.STORY, OperationType.DELETE_ANY);
        if (!isDeleteAnyStory && !(isOwn && isDeleteOwnStory)) {
            throw new AccessDeniedException();
        }
        storyRepository.updateDeletedAtById(id, new Timestamp(System.currentTimeMillis()));
        log.info("Delete Story by id: {}", id);
        return id;
    }
}
