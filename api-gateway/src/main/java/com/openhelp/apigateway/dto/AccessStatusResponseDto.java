package com.openhelp.apigateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.apigateway.enums.AccessStatusType;
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
