package com.openhelp.story.dto.access;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.story.enums.EntityType;
import com.openhelp.story.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pavel Ravvich.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessDto {

    @JsonProperty("entityType")
    private EntityType entityType;

    @JsonProperty("operationType")
    private OperationType operationType;
}