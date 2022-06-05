package com.openhelp.profile.dto.access;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pavel Ravvich.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessDto {

    @JsonProperty("isReadRoleList")
    private Boolean isReadRoleList;

    @JsonProperty("isReadOwnRole")
    private Boolean isReadOwnRole;

    @JsonProperty("isCreateRole")
    private Boolean isCreateRole;

    @JsonProperty("isUpdateRole")
    private Boolean isUpdateRole;

    @JsonProperty("isDeleteRole")
    private Boolean isDeleteRole;

    @JsonProperty("isReadUserList")
    private Boolean isReadUserList;

    @JsonProperty("isReadAnyUserDetails")
    private Boolean isReadAnyUserDetails;

    @JsonProperty("isReadAnyUserActionLog")
    private Boolean isReadAnyUserActionLog;

    @JsonProperty("isCreateUser")
    private Boolean isCreateUser;

    @JsonProperty("isUpdateOwnUser")
    private Boolean isUpdateOwnUser;

    @JsonProperty("isUpdateAnyUser")
    private Boolean isUpdateAnyUser;

    @JsonProperty("isDeleteOwnUser")
    private Boolean isDeleteOwnUser;

    @JsonProperty("isDeleteAnyUser")
    private Boolean isDeleteAnyUser;

    @JsonProperty("isReadSosList")
    private Boolean isReadSosList;

    @JsonProperty("isReadFullSosList")
    private Boolean isReadFullSosList;

    @JsonProperty("isReadAnySosDetails")
    private Boolean isReadAnySosDetails;

    @JsonProperty("isCreateSos")
    private Boolean isCreateSos;

    @JsonProperty("isUpdateOwnSos")
    private Boolean isUpdateOwnSos;

    @JsonProperty("isUpdateAnySos")
    private Boolean isUpdateAnySos;

    @JsonProperty("isDeleteOwnSos")
    private Boolean isDeleteOwnSos;

    @JsonProperty("isDeleteAnySos")
    private Boolean isDeleteAnySos;

    @JsonProperty("isReadStoryList")
    private Boolean isReadStoryList;

    @JsonProperty("isReadFullStoryList")
    private Boolean isReadFullStoryList;

    @JsonProperty("isReadAnyStoryDetails")
    private Boolean isReadAnyStoryDetails;

    @JsonProperty("isCreateStory")
    private Boolean isCreateStory;

    @JsonProperty("isUpdateOwnStory")
    private Boolean isUpdateOwnStory;

    @JsonProperty("isUpdateAnyStory")
    private Boolean isUpdateAnyStory;

    @JsonProperty("isDeleteOwnStory")
    private Boolean isDeleteOwnStory;

    @JsonProperty("isDeleteAnyStory")
    private Boolean isDeleteAnyStory;

    @JsonProperty("isReadGroupList")
    private Boolean isReadGroupList;

    @JsonProperty("isReadFullGroupList")
    private Boolean isReadFullGroupList;

    @JsonProperty("isReadAnyGroupDetail")
    private Boolean isReadAnyGroupDetail;

    @JsonProperty("isSendGroupRequest")
    private Boolean isSendGroupRequest;

    @JsonProperty("isSendGroupInvite")
    private Boolean isSendGroupInvite;

    @JsonProperty("isCreateGroup")
    private Boolean isCreateGroup;

    @JsonProperty("isUpdateOwnGroup")
    private Boolean isUpdateOwnGroup;

    @JsonProperty("isUpdateAnyGroup")
    private Boolean isUpdateAnyGroup;

    @JsonProperty("isDeleteOwnGroup")
    private Boolean isDeleteOwnGroup;

    @JsonProperty("isDeleteAnyGroup")
    private Boolean isDeleteAnyGroup;
}
