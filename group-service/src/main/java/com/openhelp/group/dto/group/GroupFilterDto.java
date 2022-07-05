package com.openhelp.group.dto.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.group.dto.FilterDto;
import com.openhelp.group.enums.StatusType;
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
public class GroupFilterDto extends FilterDto {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("createdFrom")
    private Long createdFrom;

    @JsonProperty("createdTo")
    private Long createdTo;

    @JsonProperty("updatedFrom")
    private Long updatedFrom;

    @JsonProperty("updatedTo")
    private Long updatedTo;

    @JsonProperty("expiredFrom")
    private Long expiredFrom;

    @JsonProperty("expiredTo")
    private Long expiredTo;

    @JsonProperty("deletedFrom")
    private Long deletedFrom;

    @JsonProperty("deletedTo")
    private Long deletedTo;
}
