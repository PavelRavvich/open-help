package com.openhelp.profile.dto.access;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.profile.enums.EntityType;
import com.openhelp.profile.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Pavel Ravvich.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessStatusRequestDto {

    @NotNull(message = "userId required")
    @JsonProperty("userId")
    private Long userId;

    @NotNull(message = "entityType required")
    @JsonProperty("entityType")
    private EntityType entityType;

    @NotNull(message = "operationType required")
    @JsonProperty("operationType")
    private OperationType operationType;
}
