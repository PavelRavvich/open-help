package com.openhelp.story.service;

import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.story.StoryDto;
import com.openhelp.story.dto.story.StoryFilterDto;

/**
 * @author Pavel Ravvich.
 */
public interface StoryService {

    ListDto<StoryDto> getList(StoryFilterDto filter);

    StoryDto findById(Long id);

    Long create(StoryDto story);

    Long update(Long id, StoryDto storyDto);

    Long delete(Long id);
}
