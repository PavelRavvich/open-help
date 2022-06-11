package com.openhelp.profile.service;

import com.openhelp.profile.dto.access.AccessRequestDto;
import com.openhelp.profile.dto.access.AccessResponseDto;

/**
 * @author Pavel Ravvich.
 */
public interface AccessService {
    AccessResponseDto getAccesses(AccessRequestDto req);
}
