package com.openhelp.profile.service;

import com.openhelp.profile.dto.access.UserAccessDto;

/**
 * @author Pavel Ravvich.
 */
public interface AccessService {
    UserAccessDto getAccesses();
}
