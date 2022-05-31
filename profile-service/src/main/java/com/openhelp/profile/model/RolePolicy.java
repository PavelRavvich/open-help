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
@Table(name = "role_policies")
public class RolePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_read_role_list")
    private Boolean isReadRoleList;

    @Column(name = "is_create_role")
    private Boolean isCreateRole;

    @Column(name = "is_update_role")
    private Boolean isUpdateRole;

    @Column(name = "is_delete_role")
    private Boolean isDeleteRole;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePolicy that = (RolePolicy) o;

        if (!Objects.equals(isReadRoleList, that.isReadRoleList))
            return false;
        if (!Objects.equals(isCreateRole, that.isCreateRole)) return false;
        if (!Objects.equals(isUpdateRole, that.isUpdateRole)) return false;
        return Objects.equals(isDeleteRole, that.isDeleteRole);
    }

    @Override
    public int hashCode() {
        int result = isReadRoleList != null ? isReadRoleList.hashCode() : 0;
        result = 31 * result + (isCreateRole != null ? isCreateRole.hashCode() : 0);
        result = 31 * result + (isUpdateRole != null ? isUpdateRole.hashCode() : 0);
        result = 31 * result + (isDeleteRole != null ? isDeleteRole.hashCode() : 0);
        return result;
    }
}
