package com.openhelp.story.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Pavel Ravvich.
 */
@Component
public class DateMapper {

    public Long asLong(Timestamp ts) {
        return Objects.nonNull(ts) ? ts.getTime() : null;
    }

    public Timestamp asTimestamp(Long l) {
        return Objects.nonNull(l) ? new Timestamp(l) : null;
    }
}
