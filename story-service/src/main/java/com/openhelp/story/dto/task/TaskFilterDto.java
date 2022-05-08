package com.openhelp.story.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.story.dto.FilterDto;
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
public class TaskFilterDto extends FilterDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private String type;

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

    @JsonProperty("deletedFrom")
    private Long deletedFrom;

    @JsonProperty("deletedTo")
    private Long deletedTo;

    @JsonProperty("authorId")
    private Long authorId;

    @JsonProperty("executorId")
    private Long executorId;

    @JsonProperty("storyId")
    private Long storyId;
}
