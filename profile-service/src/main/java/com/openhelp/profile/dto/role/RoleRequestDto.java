package com.openhelp.profile.dto.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.profile.dto.access.AccessDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleRequestDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("systemName")
    private String systemName;

    @JsonProperty("access")
    private AccessDto access;
}
