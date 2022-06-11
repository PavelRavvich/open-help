package com.openhelp.profile.service;

import com.openhelp.profile.dto.access.AccessesDto;
import com.openhelp.profile.enums.EntityType;

/**
 * @author Pavel Ravvich.
 */
public interface AccessService {
    AccessesDto getAccesses(EntityType req);
}
