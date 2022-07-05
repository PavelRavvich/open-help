package com.openhelp.group.mapper;

import com.openhelp.group.dto.group.GroupDto;
import com.openhelp.group.dto.group.GroupFilterDto;
import com.openhelp.group.dto.group.GroupItemDto;
import com.openhelp.group.model.Group;
import com.openhelp.group.repository.filter.GroupFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class, MembershipMapper.class, RequestMapper.class})
public interface GroupMapper {

    GroupFilter groupFilterDtoToGroupFilter(GroupFilterDto dto);

    GroupDto groupToGroupDto(Group group);

    GroupItemDto groupToGroupItemDto(Group group);

    Group groupDtoToGroup(GroupDto dto);
}
