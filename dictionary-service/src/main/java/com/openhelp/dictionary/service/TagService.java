package com.openhelp.dictionary.service;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.tag.TagDto;
import com.openhelp.dictionary.dto.tag.TagFilterDto;

public interface TagService {

    ListDto<TagDto> getList(TagFilterDto filterDto);

    TagDto findById(Long id);

    Long create(TagDto tagDto);

    Long update(Long id, TagDto tagDto);

    Long delete(Long id);
}
