package com.openhelp.profile.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openhelp.profile.dto.FilterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFilterDto extends FilterDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("isEnabled")
    private Boolean isEnabled;

    @JsonProperty("roleIds")
    private List<Long> roleIds;

}
