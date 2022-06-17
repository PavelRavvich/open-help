package com.openhelp.story.dto.task;

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
public class TaskDto {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "title required")
    @JsonProperty("title")
    private String title;

    @JsonProperty("status")
    private String status;

    @NotNull(message = "description required")
    @JsonProperty("description")
    private String description;

    @JsonProperty("createdAt")
    private Long createdAt;

    @JsonProperty("updatedAt")
    private Long updatedAt;

    @JsonProperty("closedAt")
    private Long closedAt;

    @JsonProperty("deletedAt")
    private Long deletedAt;

    @JsonProperty("authorId")
    private Long authorId;

    @JsonProperty("executorId")
    private Long executorId;

    @NotNull(message = "storyId required")
    @JsonProperty("storyId")
    private Long storyId;
}
