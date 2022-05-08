package com.openhelp.story.mapper;

import com.openhelp.story.dto.story.StoryDto;
import com.openhelp.story.dto.story.StoryFilterDto;
import com.openhelp.story.model.Story;
import com.openhelp.story.repository.filter.StoryFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class, TaskMapper.class})
public interface StoryMapper {

    StoryDto storyToStoryDto(Story story);

    Story storyDtoToStory(StoryDto dto);

    StoryFilter storyFilterDtoToStoryFilter(StoryFilterDto dto);
}
