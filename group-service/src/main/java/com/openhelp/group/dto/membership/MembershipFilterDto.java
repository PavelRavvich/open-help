package com.openhelp.group.dto.membership;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.group.dto.FilterDto;
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
public class MembershipFilterDto extends FilterDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("groupId")
    private Long groupId;

    @JsonProperty("createdFrom")
    private Long createdFrom;

    @JsonProperty("createdTo")
    private Long createdTo;

    @JsonProperty("updatedFrom")
    private Long updatedFrom;

    @JsonProperty("updatedTo")
    private Long updatedTo;
}
