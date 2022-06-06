package com.openhelp.profile.service;

import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.dto.role.RoleFilterDto;
import com.openhelp.profile.dto.role.RoleRequestDto;

import java.util.List;

/**
 * @author Pavel Ravvich.
 */
public interface RoleService {

    RoleDto findById(Long id);

    List<RoleDto> list(RoleFilterDto filterDto);

    RoleDto findBySystemName(String systemName);

    Long create(RoleRequestDto role);

    Long update(Long id, RoleRequestDto role);
}
