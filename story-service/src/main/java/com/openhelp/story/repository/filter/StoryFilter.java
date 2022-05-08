package com.openhelp.story.repository.filter;

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
public class StoryFilter {

    private String title;

    private String type;

    private String status;

    private Timestamp createdFrom;

    private Timestamp createdTo;

    private Timestamp updatedFrom;

    private Timestamp updatedTo;

    private Timestamp closedFrom;

    private Timestamp closedTo;

    private Timestamp deletedFrom;

    private Timestamp deletedTo;

    private Long currentLocationId;

    private Long exodusLocationId;

    private Long targetLocationId;

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    public Optional<String> getStatus() {
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

    public Optional<Timestamp> getClosedFrom() {
        return Optional.ofNullable(closedFrom);
    }

    public Optional<Timestamp> getClosedTo() {
        return Optional.ofNullable(closedTo);
    }

    public Optional<Timestamp> getDeletedFrom() {
        return Optional.ofNullable(deletedFrom);
    }

    public Optional<Timestamp> getDeletedTo() {
        return Optional.ofNullable(deletedTo);
    }

    public Optional<Long> getCurrentLocationId() {
        return Optional.ofNullable(currentLocationId);
    }

    public Optional<Long> getExodusLocationId() {
        return Optional.ofNullable(exodusLocationId);
    }

    public Optional<Long> getTargetLocationId() {
        return Optional.ofNullable(targetLocationId);
    }
}
