package com.openhelp.dictionary.service;

import com.openhelp.dictionary.dto.ListDto;
import com.openhelp.dictionary.dto.tag.TagDto;
import com.openhelp.dictionary.dto.tag.TagFilterDto;
import com.openhelp.dictionary.mapper.TagMapper;
import com.openhelp.dictionary.model.Tag;
import com.openhelp.dictionary.repository.TagRepository;
import com.openhelp.dictionary.repository.filter.TagFilter;
import com.openhelp.dictionary.utils.Utils;
import com.openhelp.dictionary.validation.NoSuchTagException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.openhelp.dictionary.repository.TagRepository.TagSpecification;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    private final TagRepository tagRepository;

    @Override
    public ListDto<TagDto> getList(@NotNull TagFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(),
                filterDto.getPageSize(), Utils.getSort(filterDto));
        TagFilter filter = tagMapper.tagFilterDtoToTagFilter(filterDto);
        Page<Tag> page = tagRepository.findAll(
                new TagSpecification(filter), pageable);
        List<TagDto> items = page
                .map(tagMapper::tagToTagDto)
                .getContent();

        log.info("Get Tag list {}", items);
        return ListDto.<TagDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
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
