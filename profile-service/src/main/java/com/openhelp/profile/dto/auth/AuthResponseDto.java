package com.openhelp.profile.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.profile.dto.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponseDto {

    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("id")
    private Long id;

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


