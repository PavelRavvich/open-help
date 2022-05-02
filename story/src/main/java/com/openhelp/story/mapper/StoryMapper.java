package com.openhelp.story.mapper;

import com.openhelp.story.dto.StoryDto;
import com.openhelp.story.model.Story;
import org.mapstruct.Mapper;

/**
 * @author Pavel Ravvich.
 */
@Mapper(uses = {DateMapper.class})
public interface StoryMapper {

    StoryDto storyToStoryDto(Story story);

    Story storyDtoToStory(StoryDto dto);
}
