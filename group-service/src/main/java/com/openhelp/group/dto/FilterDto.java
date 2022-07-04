package com.openhelp.group.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FilterDto {

    @NotNull(message = "Page number required")
    @JsonProperty("pageNumber")
    private Integer pageNumber;

    @NotNull(message = "Page size required")
    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("sort")
    private SortDto sort;

}
