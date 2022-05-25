package com.openhelp.profile.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequestDto extends AuthRequestDto {

    @NotNull(message = "nickname required")
    @JsonProperty("nickname")
    public String nickname;

    @JsonProperty("roleIds")
    public List<Long> roleIds;

    @JsonProperty("isEnabled")
    public Boolean isEnabled;

}
