package com.openhelp.sos.service;

import com.openhelp.sos.dto.ListDto;
import com.openhelp.sos.dto.sos.SosDto;
import com.openhelp.sos.dto.sos.SosFilterDto;
import com.openhelp.sos.enums.EntityType;
import com.openhelp.sos.enums.OperationType;
import com.openhelp.sos.mapper.SosMapper;
import com.openhelp.sos.model.Sos;
import com.openhelp.sos.repository.SosRepository;
import com.openhelp.sos.repository.filter.SosFilter;
import com.openhelp.sos.utils.SecurityUtils;
import com.openhelp.sos.utils.Utils;
import com.openhelp.sos.validation.AccessDeniedException;
import com.openhelp.sos.validation.ConcurrentUpdateException;
import com.openhelp.sos.validation.NoSuchSosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.openhelp.sos.repository.SosRepository.SosSpecification;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SosServiceImpl implements SosService {

    private final SosRepository sosRepository;

    private final SosMapper sosMapper;

    @Override
    public ListDto<SosDto> list(@NotNull SosFilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPageNumber(),
                filterDto.getPageSize(), Utils.getSort(filterDto));
        SosFilter filter = sosMapper.sosFilterDtoToSosFilter(filterDto);
        setFilteringAccessLevel(filter);
        Page<Sos> page = sosRepository.findAll(
                new SosSpecification(filter), pageable);
        List<SosDto> items = page
                .map(sosMapper::sosToSosDto)
                .getContent();
        log.info("Get Sos list {}", items);
        return ListDto.<SosDto>builder()
                .total(page.getTotalElements())
                .items(items)
                .build();
    }

    private void setFilteringAccessLevel(SosFilter filter) {
        boolean isReadAnySos = SecurityUtils.is(OperationType.READ_ANY, EntityType.SOS);
        boolean isReadOwnSos = SecurityUtils.is(OperationType.READ_OWN, EntityType.SOS);
        if (!isReadOwnSos && !isReadAnySos) {
            throw new AccessDeniedException();
        } else if (isReadOwnSos && !isReadAnySos) {
            filter.setUserId(SecurityUtils.getUserAccess().getUserId());
        }
    }

    @Override
    public SosDto findById(@NotNull Long id) {
        SosDto sos = sosMapper.sosToSosDto(
                sosRepository.findById(id)
                        .orElseThrow(NoSuchSosException::new));
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(sos.getUserId());
        boolean isReadOwnSos = SecurityUtils.is(OperationType.READ_OWN, EntityType.SOS);
        boolean isReadAnySos = SecurityUtils.is(OperationType.READ_ANY, EntityType.SOS);
        if (!isReadAnySos && !(isOwn && isReadOwnSos)) {
            throw new AccessDeniedException();
        }
        log.info("Get Sos {}", sos);
        return sos;
    }

    @Override
    public Long create(@NotNull SosDto sosDto) {
        if (!SecurityUtils.is(OperationType.CREATE, EntityType.SOS)) {
            throw new AccessDeniedException();
        }
        Long id = sosRepository.save(sosMapper.sosDtoToSos(sosDto)).getId();
        log.info("Create Sos id: {}, {}", id, sosDto);
        return id;
    }

    @Override
    public Long update(@NotNull Long id, @NotNull SosDto dto) {
        Sos sos = sosRepository.findById(id).orElseThrow(NoSuchSosException::new);
        if (!sos.getUpdatedAt().equals(new Timestamp(dto.getUpdatedAt()))) {
            throw new ConcurrentUpdateException();
        }
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(sos.getUserId());
        boolean isUpdateOwnSos = SecurityUtils.is(OperationType.UPDATE_OWN, EntityType.SOS);
        boolean isUpdateAnySos = SecurityUtils.is(OperationType.UPDATE_ANY, EntityType.SOS);
        if (!isUpdateAnySos && !(isOwn && isUpdateOwnSos)) {
            throw new AccessDeniedException();
        }
        dto.setId(id);
        sosRepository.save(sosMapper.sosDtoToSos(dto));
        log.info("Update Sos: {}", dto);
        return id;
    }

    @Override
    public Long delete(@NotNull Long id) {
        Sos sos = sosRepository.findById(id).orElseThrow(NoSuchSosException::new);
        boolean isOwn = SecurityUtils.getUserAccess().getUserId().equals(sos.getUserId());
        boolean isDeleteOwnSos = SecurityUtils.is(OperationType.DELETE_OWN, EntityType.SOS);
        boolean isDeleteAnySos = SecurityUtils.is(OperationType.DELETE_ANY, EntityType.SOS);
        if (!isDeleteAnySos && !(isOwn && isDeleteOwnSos)) {
            throw new AccessDeniedException();
        }
        sosRepository.updateDeletedAtById(id, new Timestamp(System.currentTimeMillis()));
        log.info("Delete Sos by id: {}", id);
        return id;
    }
}
