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
public class CountryFilter {

    private String title;

    private String phoneCode;

    private String isoCode;

    private String countryCode;

    public Optional<String> getTitle() {
        return Objects.nonNull(title) && !title.isBlank()
                ? Optional.of(title) : Optional.empty();
    }

    public Optional<String> getPhoneCode() {
        return Objects.nonNull(phoneCode) && !phoneCode.isBlank()
                ? Optional.of(phoneCode) : Optional.empty();
    }

    public Optional<String> getIsoCode() {
        return Objects.nonNull(isoCode) && !isoCode.isBlank()
                ? Optional.of(isoCode) : Optional.empty();
    }

    public Optional<String> getCountryCode() {
        return Objects.nonNull(countryCode) && !countryCode.isBlank()
                ? Optional.of(countryCode) : Optional.empty();
    }
}
