package com.openhelp.profile.service;

import com.openhelp.profile.enums.EntityType;
import com.openhelp.profile.enums.OperationType;

/**
 * @author Pavel Ravvich.
 */
public interface AccessService {
    Boolean isAcceded(Long userId, EntityType entity, OperationType operation);
}
