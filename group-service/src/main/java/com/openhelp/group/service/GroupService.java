package com.openhelp.group.service;

import com.openhelp.group.dto.ListDto;
import com.openhelp.group.dto.group.GroupDto;
import com.openhelp.group.dto.group.GroupFilterDto;
import com.openhelp.group.dto.group.GroupItemDto;

/**
 * @author Pavel Ravvich.
 */
public interface GroupService {

    ListDto<GroupItemDto> list(GroupFilterDto filter);

    GroupDto findById(Long id);

    Long create(GroupDto dto);

    Long update(Long id, GroupDto dto);

    Long delete(Long id);
}
