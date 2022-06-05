package com.openhelp.profile.utils;

import com.openhelp.profile.dto.FilterDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;

import java.util.Objects;

/**
 * @author Pavel Ravvich.
 */
public class Utils {

    @NotNull
    public static Sort getSort(@NotNull FilterDto filterDto) {
        Sort sort = Sort.by("createdAt").ascending();
        if (Objects.nonNull(filterDto.getSort())
                && Objects.nonNull(filterDto.getSort().getActive())
                && Objects.nonNull(filterDto.getSort().getDirection())) {
            if (filterDto.getSort().getDirection() == Sort.Direction.ASC) {
                sort = Sort.by(filterDto.getSort().getActive()).ascending();
            } else if (filterDto.getSort().getDirection() == Sort.Direction.DESC) {
                sort = Sort.by(filterDto.getSort().getActive()).descending();
            }
        }
        return sort;
    }
}
