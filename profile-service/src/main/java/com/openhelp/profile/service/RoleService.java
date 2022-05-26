package com.openhelp.profile.service;


import com.openhelp.profile.dto.role.RoleDto;
import com.openhelp.profile.dto.role.RoleFilterDto;

import java.util.List;

/**
 * @author Pavel Ravvich.
 */
public interface RoleService {

    List<RoleDto> list(RoleFilterDto filterDto);

    RoleDto findBySystemName(String systemName);
}
