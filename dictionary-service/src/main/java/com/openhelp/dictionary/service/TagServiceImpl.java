package com.openhelp.dictionary.service;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.tag.TagDto;
import com.openhelp.dictionary.dto.tag.TagFilterDto;
import com.openhelp.dictionary.mapper.TagMapper;
import com.openhelp.dictionary.repository.TagRepository;
import com.openhelp.dictionary.validation.NoSuchTagException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    private final TagRepository tagRepository;

    @Override
    public ListDto<TagDto> getList(@NotNull TagFilterDto filterDto) {
        // TODO: 5/9/2022 impl
        return null;
    }

    @Override
    public TagDto findById(@NotNull Long id) {
        log.debug("Find Tag by id: {}", id);
        return tagMapper.tagToTagDto(tagRepository.findById(id)
                .orElseThrow(NoSuchTagException::new));
    }

    @Override
    public Long create(@NotNull TagDto dto) {
        log.debug("Create new Tag: {}", dto);
        return tagRepository.save(tagMapper.tagDtoToTag(dto)).getId();
    }

    @Override
    public Long update(@NotNull Long id, @NotNull TagDto dto) {
        log.debug("Update Tag id: {}, {}", id, dto);
        if (!tagRepository.existsById(id)) {
            throw new NoSuchTagException();
        }
        return tagRepository.save(tagMapper.tagDtoToTag(dto)).getId();
    }

    @Override
    public Long delete(@NotNull Long id) {
        log.debug("Delete Tag by id: {}", id);
        if (!tagRepository.existsById(id)) {
            throw new NoSuchTagException();
        }
        tagRepository.updateDeletedAtById(id, new Timestamp(System.currentTimeMillis()));
        return id;
    }
}
