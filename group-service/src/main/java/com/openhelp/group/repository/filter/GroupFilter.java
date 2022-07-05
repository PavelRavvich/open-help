package com.openhelp.group.repository.filter;

import com.openhelp.group.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author Pavel Ravvich.
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupFilter {

    private Long userId;

    private String title;

    private StatusType status;

    private Timestamp createdFrom;

    private Timestamp createdTo;

    private Timestamp updatedFrom;

    private Timestamp updatedTo;

    private Timestamp expiredFrom;

    private Timestamp expiredTo;

    private Timestamp deletedFrom;

    private Timestamp deletedTo;

    public Optional<Long> getUserId() {
        return Optional.ofNullable(userId);
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<StatusType> getStatus() {
        return Optional.ofNullable(status);
    }

    public Optional<Timestamp> getCreatedFrom() {
        return Optional.ofNullable(createdFrom);
    }

    public Optional<Timestamp> getCreatedTo() {
        return Optional.ofNullable(createdTo);
    }

    public Optional<Timestamp> getUpdatedFrom() {
        return Optional.ofNullable(updatedFrom);
    }

    public Optional<Timestamp> getUpdatedTo() {
        return Optional.ofNullable(updatedTo);
    }

    public Optional<Timestamp> getExpiredFrom() {
        return Optional.ofNullable(expiredFrom);
    }

    public Optional<Timestamp> getExpiredTo() {
        return Optional.ofNullable(expiredTo);
    }

    public Optional<Timestamp> getDeletedFrom() {
        return Optional.ofNullable(deletedFrom);
    }

    public Optional<Timestamp> getDeletedTo() {
        return Optional.ofNullable(deletedTo);
    }
}
