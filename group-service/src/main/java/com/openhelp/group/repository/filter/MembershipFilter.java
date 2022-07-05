package com.openhelp.group.repository.filter;

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
public class MembershipFilter {

    private Long userId;

    private Long groupId;

    private Timestamp createdFrom;

    private Timestamp createdTo;

    private Timestamp updatedFrom;

    private Timestamp updatedTo;

    public Optional<Long> getUserId() {
        return Optional.ofNullable(userId);
    }

    public Optional<Long> getGroupId() {
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
