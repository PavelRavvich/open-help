package com.openhelp.story.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author Pavel Ravvich.
 *
 * Rest representation for collections with pagination support.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDto<T> {

    /**
     * Amount available pages.
     */
    @JsonProperty("total")
    private long total;

    /**
     * Data collection for current page.
     */
    @JsonProperty("items")
    private Collection<T> items;

}