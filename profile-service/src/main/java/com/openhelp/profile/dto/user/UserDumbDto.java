package com.openhelp.profile.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDumbDto {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "Обязательный параметр")
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("isEnabled")
    private Boolean isEnabled;
}
