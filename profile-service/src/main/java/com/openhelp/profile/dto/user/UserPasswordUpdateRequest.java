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
public class UserPasswordUpdateRequest {

    @NotNull(message = "oldPassword required")
    @JsonProperty("oldPassword")
    private String oldPassword;

    @NotNull(message = "newPassword required")
    @JsonProperty("newPassword")
    private String newPassword;
}
