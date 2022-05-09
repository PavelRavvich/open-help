package com.openhelp.dictionary.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        return Optional.ofNullable(title);
    }

}
