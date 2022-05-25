package com.openhelp.profile.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleFilter {

    private String title;

    public Optional<String> getTitle() {
        return Objects.isNull(title) || title.isEmpty() ? Optional.empty() : Optional.of(title);
    }

}