package com.openhelp.apigateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.apigateway.enums.EntityType;
import com.openhelp.apigateway.enums.OperationType;
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
public class AccessStatusRequestDto {

    @NotNull(message = "entityType required")
    @JsonProperty("entityType")
    private EntityType entityType;

    @NotNull(message = "operationType required")
    @JsonProperty("operationType")
    private OperationType operationType;
}
