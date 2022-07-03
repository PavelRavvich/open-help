package com.openhelp.sos.service;

import com.openhelp.sos.dto.ListDto;
import com.openhelp.sos.dto.sos.SosDto;
import com.openhelp.sos.dto.sos.SosFilterDto;

/**
 * @author Pavel Ravvich.
 */
public interface SosService {

    ListDto<SosDto> list(SosFilterDto filter);

    SosDto findById(Long id);

    Long create(SosDto sos);

    Long update(Long id, SosDto sos);

    Long delete(Long id);
}
