package com.openhelp.dictionary.mapper;

import com.openhelp.dictionary.dto.tag.TagDto;
import com.openhelp.dictionary.dto.tag.TagFilterDto;
import com.openhelp.dictionary.model.Tag;
import com.openhelp.dictionary.repository.filter.TagFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class})
public interface TagMapper {

    Tag tagDtoToTag(TagDto dto);

    TagDto tagToTagDto(Tag tag);

    TagFilter tagFilterDtoToTagFilter(TagFilterDto dto);
}
