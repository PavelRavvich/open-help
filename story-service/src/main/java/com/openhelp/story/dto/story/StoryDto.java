package com.openhelp.story.dto.story;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.story.dto.task.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

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

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("currentLocationId")
    private Long currentLocationId;

    @JsonProperty("exodusLocationId")
    private Long exodusLocationId;

    @JsonProperty("targetLocationId")
    private Long targetLocationId;

    @JsonProperty("tasks")
    private List<TaskDto> tasks;
}
