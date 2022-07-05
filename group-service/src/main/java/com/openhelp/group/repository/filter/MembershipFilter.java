package com.openhelp.group.repository.filter;

import com.openhelp.group.model.Group;
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
public class MembershipFilter {

    private Long userId;

    private Long groupId;

    private Timestamp createdFrom;

    private Timestamp createdTo;

    private Timestamp updatedFrom;

    private Timestamp updatedTo;

    private Timestamp deletedFrom;

    private Timestamp deletedTo;

    public Optional<Long> getUserId() {
        return Optional.ofNullable(userId);
    }

    public Optional<Group> getGroup() {
        return Objects.nonNull(groupId)
                ? Optional.of(Group.builder().id(groupId).build())
                : Optional.empty();
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

    public Optional<Timestamp> getDeletedFrom() {
        return Optional.ofNullable(deletedFrom);
    }

    public Optional<Timestamp> getDeletedTo() {
        return Optional.ofNullable(deletedTo);
    }
}
