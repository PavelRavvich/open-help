package com.openhelp.dictionary.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Pavel Ravvich.
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagFilter {

    private String title;

    public Optional<String> getTitle() {
        return Objects.nonNull(title) && !title.isBlank()
                ? Optional.of(title) : Optional.empty();
    }

}
