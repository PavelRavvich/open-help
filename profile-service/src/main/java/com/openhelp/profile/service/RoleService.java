package com.openhelp.profile.service;


import com.openhelp.profile.model.Role;
import com.openhelp.profile.repository.filter.RoleFilter;

import java.util.List;

/**
 * @author Pavel Ravvich.
 */
public interface RoleService {

    List<Role> list(RoleFilter filter);

    Role findBySystemName(String systemName);
}
