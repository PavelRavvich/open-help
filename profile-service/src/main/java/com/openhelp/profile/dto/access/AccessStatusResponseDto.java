package com.openhelp.profile.dto.access;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.profile.enums.AccessStatusType;
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
public class AccessStatusResponseDto {

    @JsonProperty("requestedAt")
    private Long requestedAt;

    @JsonProperty("accessStatus")
    private AccessStatusType accessStatus;
}
