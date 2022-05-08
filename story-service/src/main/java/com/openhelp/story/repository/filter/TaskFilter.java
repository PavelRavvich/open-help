package com.openhelp.story.repository.filter;

import com.openhelp.story.model.Story;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Pavel Ravvich.
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskFilter {

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

    private Long authorId;

    private Long executorId;

    private Story story;

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

    public Optional<Long> getAuthorId() {
        return Optional.ofNullable(authorId);
    }

    public Optional<Long> getExecutorId() {
        return Optional.ofNullable(executorId);
    }

    public Optional<Story> getStory() {
        return Objects.nonNull(story) && Objects.nonNull(story.getId())
                ? Optional.of(story)
                : Optional.empty();
    }
}
