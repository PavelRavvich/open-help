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
public class RequestFilter {

    private String title;

    private StatusType status;

    private String description;

    private Timestamp userId;

    private Timestamp groupId;

    private Timestamp createdFrom;

    private Timestamp createdTo;

    private Timestamp updatedFrom;

    private Timestamp updatedTo;

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<StatusType> getStatus() {
        return Optional.ofNullable(status);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<Timestamp> getUserId() {
        return Optional.ofNullable(userId);
    }

    public Optional<Timestamp> getGroupId() {
        return Optional.ofNullable(groupId);
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
}
