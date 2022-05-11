package com.openhelp.dictionary.dto.country;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.dictionary.dto.FilterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavel Ravvich.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryFilterDto extends FilterDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("phoneCode")
    private String phoneCode;

    @JsonProperty("isoCode")
    private String isoCode;

    @JsonProperty("countryCode")
    private String countryCode;
}
