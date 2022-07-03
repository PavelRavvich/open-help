package com.openhelp.sos.dto.sos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.sos.dto.FilterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavel Ravvich.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SosFilterDto extends FilterDto {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("status")
    private String status;

    @JsonProperty("createdFrom")
    private Long createdFrom;

    @JsonProperty("createdTo")
    private Long createdTo;

    @JsonProperty("updatedFrom")
    private Long updatedFrom;

    @JsonProperty("updatedTo")
    private Long updatedTo;

    @JsonProperty("closedFrom")
    private Long closedFrom;

    @JsonProperty("closedTo")
    private Long closedTo;

}
