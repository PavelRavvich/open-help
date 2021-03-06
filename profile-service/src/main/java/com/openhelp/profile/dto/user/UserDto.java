package com.openhelp.profile.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.profile.dto.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "username required")
    @JsonProperty("username")
    private String username;

    @NotNull(message = "nickname required")
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("isEnabled")
    private Boolean isEnabled;

    @Valid
    @NotNull(message = "nickname required")
    @JsonProperty("roles")
    private List<RoleDto> roles;
}
