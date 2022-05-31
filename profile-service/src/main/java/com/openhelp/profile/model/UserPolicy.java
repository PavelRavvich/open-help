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
@Table(name = "user_policies")
public class UserPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPolicy that = (UserPolicy) o;

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
        return Objects.equals(isDeleteAnyUser, that.isDeleteAnyUser);
    }

    @Override
    public int hashCode() {
        int result = isReadUserList != null ? isReadUserList.hashCode() : 0;
        result = 31 * result + (isReadAnyUserDetails != null ? isReadAnyUserDetails.hashCode() : 0);
        result = 31 * result + (isReadAnyUserActionLog != null ? isReadAnyUserActionLog.hashCode() : 0);
        result = 31 * result + (isCreateUser != null ? isCreateUser.hashCode() : 0);
        result = 31 * result + (isUpdateOwnUser != null ? isUpdateOwnUser.hashCode() : 0);
        result = 31 * result + (isUpdateAnyUser != null ? isUpdateAnyUser.hashCode() : 0);
        result = 31 * result + (isDeleteOwnUser != null ? isDeleteOwnUser.hashCode() : 0);
        result = 31 * result + (isDeleteAnyUser != null ? isDeleteAnyUser.hashCode() : 0);
        return result;
    }
}
