package com.openhelp.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Pavel Ravvich.
 */
@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "access_policies")
public class AccessPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_read_role_list")
    private Boolean isReadRoleList;

    @Column(name = "is_read_own_role")
    private Boolean isReadOwnRole;

    @Column(name = "is_create_role")
    private Boolean isCreateRole;

    @Column(name = "is_update_role")
    private Boolean isUpdateRole;

    @Column(name = "is_delete_role")
    private Boolean isDeleteRole;

    @Column(name = "is_read_user_list")
    private Boolean isReadUserList;

    @Column(name = "is_read_any_user_details")
    private Boolean isReadAnyUserDetails;

    @Column(name = "is_read_any_user_action_log")
    private Boolean isReadAnyUserActionLog;

    @Column(name = "is_create_user")
    private Boolean isCreateUser;

    @Column(name = "is_update_own_user")
    private Boolean isUpdateOwnUser;

    @Column(name = "is_update_any_user")
    private Boolean isUpdateAnyUser;

    @Column(name = "is_delete_own_user")
    private Boolean isDeleteOwnUser;

    @Column(name = "is_delete_any_user")
    private Boolean isDeleteAnyUser;

    @Column(name = "is_read_sos_list")
    private Boolean isReadSosList;

    @Column(name = "is_read_full_sos_list")
    private Boolean isReadFullSosList;

    @Column(name = "is_read_any_sos_details")
    private Boolean isReadAnySosDetails;

    @Column(name = "is_create_sos")
    private Boolean isCreateSos;

    @Column(name = "is_update_own_sos")
    private Boolean isUpdateOwnSos;

    @Column(name = "is_update_any_sos")
    private Boolean isUpdateAnySos;

    @Column(name = "is_delete_own_sos")
    private Boolean isDeleteOwnSos;

    @Column(name = "is_delete_any_sos")
    private Boolean isDeleteAnySos;

    @Column(name = "is_read_story_list")
    private Boolean isReadStoryList;

    @Column(name = "is_read_full_story_list")
    private Boolean isReadFullStoryList;

    @Column(name = "is_read_any_story_details")
    private Boolean isReadAnyStoryDetails;

    @Column(name = "is_create_story")
    private Boolean isCreateStory;

    @Column(name = "is_update_own_story")
    private Boolean isUpdateOwnStory;

    @Column(name = "is_update_any_story")
    private Boolean isUpdateAnyStory;

    @Column(name = "is_delete_own_story")
    private Boolean isDeleteOwnStory;

    @Column(name = "is_delete_any_story")
    private Boolean isDeleteAnyStory;

    @Column(name = "is_read_group_list")
    private Boolean isReadGroupList;

    @Column(name = "is_read_full_group_list")
    private Boolean isReadFullGroupList;

    @Column(name = "is_read_any_group_detail")
    private Boolean isReadAnyGroupDetail;

    @Column(name = "is_send_group_request")
    private Boolean isSendGroupRequest;

    @Column(name = "is_send_group_invite")
    private Boolean isSendGroupInvite;

    @Column(name = "is_create_group")
    private Boolean isCreateGroup;

    @Column(name = "is_update_own_group")
    private Boolean isUpdateOwnGroup;

    @Column(name = "is_update_any_group")
    private Boolean isUpdateAnyGroup;

    @Column(name = "is_delete_own_group")
    private Boolean isDeleteOwnGroup;

    @Column(name = "is_delete_any_group")
    private Boolean isDeleteAnyGroup;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessPolicy that = (AccessPolicy) o;

        if (!Objects.equals(isReadRoleList, that.isReadRoleList))
            return false;
        if (!Objects.equals(isReadOwnRole, that.isReadOwnRole))
            return false;
        if (!Objects.equals(isCreateRole, that.isCreateRole)) return false;
        if (!Objects.equals(isUpdateRole, that.isUpdateRole)) return false;
        if (!Objects.equals(isDeleteRole, that.isDeleteRole)) return false;
        if (!Objects.equals(isReadUserList, that.isReadUserList))
            return false;
        if (!Objects.equals(isReadAnyUserDetails, that.isReadAnyUserDetails))
            return false;
        if (!Objects.equals(isReadAnyUserActionLog, that.isReadAnyUserActionLog))
            return false;
        if (!Objects.equals(isCreateUser, that.isCreateUser)) return false;
        if (!Objects.equals(isUpdateOwnUser, that.isUpdateOwnUser))
            return false;
        if (!Objects.equals(isUpdateAnyUser, that.isUpdateAnyUser))
            return false;
        if (!Objects.equals(isDeleteOwnUser, that.isDeleteOwnUser))
            return false;
        if (!Objects.equals(isDeleteAnyUser, that.isDeleteAnyUser))
            return false;
        if (!Objects.equals(isReadSosList, that.isReadSosList))
            return false;
        if (!Objects.equals(isReadFullSosList, that.isReadFullSosList))
            return false;
        if (!Objects.equals(isReadAnySosDetails, that.isReadAnySosDetails))
            return false;
        if (!Objects.equals(isCreateSos, that.isCreateSos)) return false;
        if (!Objects.equals(isUpdateOwnSos, that.isUpdateOwnSos))
            return false;
        if (!Objects.equals(isUpdateAnySos, that.isUpdateAnySos))
            return false;
        if (!Objects.equals(isDeleteOwnSos, that.isDeleteOwnSos))
            return false;
        if (!Objects.equals(isDeleteAnySos, that.isDeleteAnySos))
            return false;
        if (!Objects.equals(isReadStoryList, that.isReadStoryList))
            return false;
        if (!Objects.equals(isReadFullStoryList, that.isReadFullStoryList))
            return false;
        if (!Objects.equals(isReadAnyStoryDetails, that.isReadAnyStoryDetails))
            return false;
        if (!Objects.equals(isCreateStory, that.isCreateStory))
            return false;
        if (!Objects.equals(isUpdateOwnStory, that.isUpdateOwnStory))
            return false;
        if (!Objects.equals(isUpdateAnyStory, that.isUpdateAnyStory))
            return false;
        if (!Objects.equals(isDeleteOwnStory, that.isDeleteOwnStory))
            return false;
        if (!Objects.equals(isDeleteAnyStory, that.isDeleteAnyStory))
            return false;
        if (!Objects.equals(isReadGroupList, that.isReadGroupList))
            return false;
        if (!Objects.equals(isReadFullGroupList, that.isReadFullGroupList))
            return false;
        if (!Objects.equals(isReadAnyGroupDetail, that.isReadAnyGroupDetail))
            return false;
        if (!Objects.equals(isSendGroupRequest, that.isSendGroupRequest))
            return false;
        if (!Objects.equals(isSendGroupInvite, that.isSendGroupInvite))
            return false;
        if (!Objects.equals(isCreateGroup, that.isCreateGroup))
            return false;
        if (!Objects.equals(isUpdateOwnGroup, that.isUpdateOwnGroup))
            return false;
        if (!Objects.equals(isUpdateAnyGroup, that.isUpdateAnyGroup))
            return false;
        if (!Objects.equals(isDeleteOwnGroup, that.isDeleteOwnGroup))
            return false;
        return Objects.equals(isDeleteAnyGroup, that.isDeleteAnyGroup);
    }

    @Override
    public int hashCode() {
        int result = isReadRoleList != null ? isReadRoleList.hashCode() : 0;
        result = 31 * result + (isReadOwnRole != null ? isReadOwnRole.hashCode() : 0);
        result = 31 * result + (isCreateRole != null ? isCreateRole.hashCode() : 0);
        result = 31 * result + (isUpdateRole != null ? isUpdateRole.hashCode() : 0);
        result = 31 * result + (isDeleteRole != null ? isDeleteRole.hashCode() : 0);
        result = 31 * result + (isReadUserList != null ? isReadUserList.hashCode() : 0);
        result = 31 * result + (isReadAnyUserDetails != null ? isReadAnyUserDetails.hashCode() : 0);
        result = 31 * result + (isReadAnyUserActionLog != null ? isReadAnyUserActionLog.hashCode() : 0);
        result = 31 * result + (isCreateUser != null ? isCreateUser.hashCode() : 0);
        result = 31 * result + (isUpdateOwnUser != null ? isUpdateOwnUser.hashCode() : 0);
        result = 31 * result + (isUpdateAnyUser != null ? isUpdateAnyUser.hashCode() : 0);
        result = 31 * result + (isDeleteOwnUser != null ? isDeleteOwnUser.hashCode() : 0);
        result = 31 * result + (isDeleteAnyUser != null ? isDeleteAnyUser.hashCode() : 0);
        result = 31 * result + (isReadSosList != null ? isReadSosList.hashCode() : 0);
        result = 31 * result + (isReadFullSosList != null ? isReadFullSosList.hashCode() : 0);
        result = 31 * result + (isReadAnySosDetails != null ? isReadAnySosDetails.hashCode() : 0);
        result = 31 * result + (isCreateSos != null ? isCreateSos.hashCode() : 0);
        result = 31 * result + (isUpdateOwnSos != null ? isUpdateOwnSos.hashCode() : 0);
        result = 31 * result + (isUpdateAnySos != null ? isUpdateAnySos.hashCode() : 0);
        result = 31 * result + (isDeleteOwnSos != null ? isDeleteOwnSos.hashCode() : 0);
        result = 31 * result + (isDeleteAnySos != null ? isDeleteAnySos.hashCode() : 0);
        result = 31 * result + (isReadStoryList != null ? isReadStoryList.hashCode() : 0);
        result = 31 * result + (isReadFullStoryList != null ? isReadFullStoryList.hashCode() : 0);
        result = 31 * result + (isReadAnyStoryDetails != null ? isReadAnyStoryDetails.hashCode() : 0);
        result = 31 * result + (isCreateStory != null ? isCreateStory.hashCode() : 0);
        result = 31 * result + (isUpdateOwnStory != null ? isUpdateOwnStory.hashCode() : 0);
        result = 31 * result + (isUpdateAnyStory != null ? isUpdateAnyStory.hashCode() : 0);
        result = 31 * result + (isDeleteOwnStory != null ? isDeleteOwnStory.hashCode() : 0);
        result = 31 * result + (isDeleteAnyStory != null ? isDeleteAnyStory.hashCode() : 0);
        result = 31 * result + (isReadGroupList != null ? isReadGroupList.hashCode() : 0);
        result = 31 * result + (isReadFullGroupList != null ? isReadFullGroupList.hashCode() : 0);
        result = 31 * result + (isReadAnyGroupDetail != null ? isReadAnyGroupDetail.hashCode() : 0);
        result = 31 * result + (isSendGroupRequest != null ? isSendGroupRequest.hashCode() : 0);
        result = 31 * result + (isSendGroupInvite != null ? isSendGroupInvite.hashCode() : 0);
        result = 31 * result + (isCreateGroup != null ? isCreateGroup.hashCode() : 0);
        result = 31 * result + (isUpdateOwnGroup != null ? isUpdateOwnGroup.hashCode() : 0);
        result = 31 * result + (isUpdateAnyGroup != null ? isUpdateAnyGroup.hashCode() : 0);
        result = 31 * result + (isDeleteOwnGroup != null ? isDeleteOwnGroup.hashCode() : 0);
        result = 31 * result + (isDeleteAnyGroup != null ? isDeleteAnyGroup.hashCode() : 0);
        return result;
    }
}
