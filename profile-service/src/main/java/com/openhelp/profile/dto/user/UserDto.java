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

    @NotNull(message = "Обязательный параметр")
    @JsonProperty("username")
    private String username;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("isEnabled")
    private Boolean isEnabled;

    @Valid
    @JsonProperty("roles")
    private List<RoleDto> roles;
}
