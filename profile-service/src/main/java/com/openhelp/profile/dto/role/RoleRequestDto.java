package com.openhelp.profile.dto.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @JsonProperty("accessIds")
    private List<Long> accessIds;
}
