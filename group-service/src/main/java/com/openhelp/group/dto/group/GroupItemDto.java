package com.openhelp.group.dto.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.group.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Pavel Ravvich.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupItemDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("membersLimit")
    private Integer membersLimit;

    @JsonProperty("description")
    private String description;

    @JsonProperty("latitude")
    private BigDecimal latitude;

    @JsonProperty("longitude")
    private BigDecimal longitude;

    @JsonProperty("createdAt")
    private Long createdAt;

    @JsonProperty("updatedAt")
    private Long updatedAt;

    @JsonProperty("closedAt")
    private Long closedAt;

    @JsonProperty("expiredAt")
    private Long expiredAt;

    @JsonProperty("deletedAt")
    private Long deletedAt;
}
