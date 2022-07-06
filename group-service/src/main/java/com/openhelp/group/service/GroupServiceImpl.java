package com.openhelp.group.service;

import com.openhelp.group.dto.ListDto;
import com.openhelp.group.dto.group.GroupDto;
import com.openhelp.group.dto.group.GroupFilterDto;
import com.openhelp.group.dto.group.GroupItemDto;
import com.openhelp.group.enums.EntityType;
import com.openhelp.group.enums.OperationType;
import com.openhelp.group.mapper.GroupMapper;
import com.openhelp.group.model.Group;
import com.openhelp.group.repository.GroupRepository;
import com.openhelp.group.repository.GroupRepository.GroupSpecification;
import com.openhelp.group.repository.filter.GroupFilter;
import com.openhelp.group.utils.SecurityUtils;
import com.openhelp.group.utils.Utils;
import com.openhelp.group.validation.AccessDeniedException;
import com.openhelp.group.validation.ConcurrentUpdateException;
import com.openhelp.group.validation.NoSuchGroupException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    @Override
    public ListDto<GroupItemDto> list(@NotNull GroupFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(),
                filterDto.getPageSize(), Utils.getSort(filterDto));
        GroupFilter filter = groupMapper.groupFilterDtoToGroupFilter(filterDto);
        setFilteringAccessLevel(filter);
        Page<Group> page = groupRepository.findAll(
                new GroupSpecification(filter), pageable);
        List<GroupItemDto> items = page
                .map(groupMapper::groupToGroupItemDto)
                .getContent();
        log.info("Get Group list {}", items);
        return ListDto.<GroupItemDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
    }

    private void setFilteringAccessLevel(GroupFilter filter) {
        boolean isReadAnyGroup = SecurityUtils.is(OperationType.READ_ANY, EntityType.GROUP);
        boolean isReadOwnGroup = SecurityUtils.is(OperationType.READ_OWN, EntityType.GROUP);
        if (!isReadOwnGroup && !isReadAnyGroup) {
            throw new AccessDeniedException();
        } else if (isReadOwnGroup && !isReadAnyGroup) {
            filter.setUserId(SecurityUtils.getUserAccess().getUserId());
        }
    }

    @Override
    public GroupDto findById(@NotNull Long id) {
        GroupDto group = groupMapper.groupToGroupDto(
                groupRepository.findById(id)
                        .orElseThrow(NoSuchGroupException::new));
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(group.getUserId());
        boolean isReadOwnGroup = SecurityUtils.is(OperationType.READ_OWN, EntityType.GROUP);
        boolean isReadAnyGroup = SecurityUtils.is(OperationType.READ_ANY, EntityType.GROUP);
        if (!isReadAnyGroup && !(isOwn && isReadOwnGroup)) {
            throw new AccessDeniedException();
        }
        log.info("Get Group {}", group);
        return group;
    }

    @Override
    public Long create(@NotNull GroupDto groupDto) {
        if (!SecurityUtils.is(OperationType.CREATE, EntityType.GROUP)) {
            throw new AccessDeniedException();
        }
        Long id = groupRepository.save(groupMapper.groupDtoToGroup(groupDto)).getId();
        log.info("Create Group id: {}, {}", id, groupDto);
        return id;
    }

    @Override
    public Long update(@NotNull Long id, @NotNull GroupDto dto) {
        Group group = groupRepository.findById(id).orElseThrow(NoSuchGroupException::new);
        if (!group.getUpdatedAt().equals(new Timestamp(dto.getUpdatedAt()))) {
            throw new ConcurrentUpdateException();
        }
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(group.getUserId());
        boolean isUpdateOwnGroup = SecurityUtils.is(OperationType.UPDATE_OWN, EntityType.SOS);
        boolean isUpdateAnyGroup = SecurityUtils.is(OperationType.UPDATE_ANY, EntityType.SOS);
        if (!isUpdateAnyGroup && !(isOwn && isUpdateOwnGroup)) {
            throw new AccessDeniedException();
        }
        dto.setId(id);
        groupRepository.save(groupMapper.groupDtoToGroup(dto));
        log.info("Update Group: {}", dto);
        return id;
    }

    @Override
    public Long delete(@NotNull Long id) {
        Group group = groupRepository.findById(id).orElseThrow(NoSuchGroupException::new);
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(group.getUserId());
        boolean isDeleteOwnGroup = SecurityUtils.is(OperationType.DELETE_OWN, EntityType.GROUP);
        boolean isDeleteAnyGroup = SecurityUtils.is(OperationType.DELETE_ANY, EntityType.GROUP);
        if (!isDeleteAnyGroup && !(isOwn && isDeleteOwnGroup)) {
            throw new AccessDeniedException();
        }
        groupRepository.updateDeletedAtById(id, new Timestamp(System.currentTimeMillis()));
        log.info("Delete Group by id: {}", id);
        return id;
    }
}
