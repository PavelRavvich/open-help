package com.openhelp.profile.service;

import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.dto.role.RoleFilterDto;
import com.openhelp.profile.mapper.RoleMapper;
import com.openhelp.profile.model.Access;
import com.openhelp.profile.model.Role;
import com.openhelp.profile.repository.RoleRepository;
import com.openhelp.profile.repository.RoleRepository.RoleSpecification;
import com.openhelp.profile.repository.filter.RoleFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final RoleRepository roleRepository;

    @Override
    public RoleDto findById(@NotNull Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::roleToRoleDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<RoleDto> list(@NotNull RoleFilterDto filterDto) {
        RoleFilter filter = roleMapper.toRoleFilter(filterDto);
        RoleSpecification specification = new RoleSpecification(filter);
        Specification<Role> where = Specification.where(specification);
        return roleRepository.findAll(where)
                .stream()
                .map(roleMapper::roleToRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto findBySystemName(@NotNull String systemName) {
        return roleRepository.findBySystemName(systemName)
                .map(roleMapper::roleToRoleDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Long create(@NotNull RoleDto dto) {
        return roleRepository.save(
                Role.builder()
                        .access(Access.builder().build())
                        .systemName(dto.getSystemName())
                        .title(dto.getTitle())
                        .build()
        ).getId();
    }

    @Override
    @Transactional
    public Long update(@NotNull Long id, @NotNull RoleDto dto) {
        roleRepository.save(
                roleRepository
                        .findById(id)
                        .map(role -> {
                            Role update = roleMapper.roleDtoToRole(dto);
                            update.setSystemName(update.getSystemName());
                            update.setAccess(update.getAccess());
                            update.setTitle(update.getTitle());
                            return role;
                        }).orElseThrow(NoSuchElementException::new));
        return id;
    }
}
