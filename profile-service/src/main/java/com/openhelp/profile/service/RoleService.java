package com.openhelp.profile.service;

import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.dto.role.RoleFilterDto;

import java.util.List;

/**
 * @author Pavel Ravvich.
 */
public interface RoleService {

    RoleDto findById(Long id);

    List<RoleDto> list(RoleFilterDto filterDto);

    RoleDto findBySystemName(String systemName);

    Long create(RoleDto role);

    Long update(Long id, RoleDto role);
}
