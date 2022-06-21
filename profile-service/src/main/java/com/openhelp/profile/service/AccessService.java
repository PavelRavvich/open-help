package com.openhelp.profile.service;

import com.openhelp.profile.utils.SecurityUtils.UserAccessDto;

/**
 * @author Pavel Ravvich.
 */
public interface AccessService {
    UserAccessDto getAccesses();
}
