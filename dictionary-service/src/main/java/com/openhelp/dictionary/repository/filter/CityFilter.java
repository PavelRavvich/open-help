package com.openhelp.dictionary.repository.filter;

import com.openhelp.dictionary.model.Country;
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
public class CityFilter {

    private String title;

    private String phoneCode;

    private Country country;

    public Optional<String> getTitle() {
        return Objects.nonNull(title) && !title.isBlank()
                ? Optional.of(title) : Optional.empty();
    }

    public Optional<String> getPhoneCode() {
        return Objects.nonNull(phoneCode) && !phoneCode.isBlank()
                ? Optional.of(phoneCode) : Optional.empty();
    }

    public Optional<Country> getCountry() {
        return Objects.nonNull(country) && Objects.nonNull(country.getId())
                ? Optional.of(country)
                : Optional.empty();
    }
}
