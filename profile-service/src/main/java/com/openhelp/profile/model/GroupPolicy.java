package com.openhelp.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "group_policies")
public class GroupPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_read_group_list")
    private Boolean isReadGroupList;

    @Column(name = "is_read_any_group_detail")
    private Boolean isReadGroupAnyGroupDetail;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupPolicy that = (GroupPolicy) o;

        if (!Objects.equals(isReadGroupList, that.isReadGroupList))
            return false;
        if (!Objects.equals(isReadGroupAnyGroupDetail, that.isReadGroupAnyGroupDetail))
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
        int result = isReadGroupList != null ? isReadGroupList.hashCode() : 0;
        result = 31 * result + (isReadGroupAnyGroupDetail != null ? isReadGroupAnyGroupDetail.hashCode() : 0);
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
