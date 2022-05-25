package com.openhelp.profile.service;

import com.openhelp.profile.model.Role;
import com.openhelp.profile.repository.RoleRepository;
import com.openhelp.profile.repository.filter.RoleFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * @author Pavel Ravvich.
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> list(@NotNull RoleFilter filter) {
        return roleRepository.findAll(where(new RoleRepository.RoleSpecification(filter)));
    }

    @Override
    public Role findBySystemName(@NotNull String systemName) {
        return roleRepository.findBySystemName(systemName)
                .orElseThrow(NoSuchElementException::new);
    }
}
