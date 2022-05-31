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
@Table(name = "sos_policies")
public class SosPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_read_sos_list")
    private Boolean isReadSosList;

    @Column(name = "is_read_full_sos_list")
    private Boolean isReadFullSosList;

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

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SosPolicy sosPolicy = (SosPolicy) o;

        if (!Objects.equals(isReadSosList, sosPolicy.isReadSosList))
            return false;
        if (!Objects.equals(isReadFullSosList, sosPolicy.isReadFullSosList))
            return false;
        if (!Objects.equals(isCreateSos, sosPolicy.isCreateSos))
            return false;
        if (!Objects.equals(isUpdateOwnSos, sosPolicy.isUpdateOwnSos))
            return false;
        if (!Objects.equals(isUpdateAnySos, sosPolicy.isUpdateAnySos))
            return false;
        if (!Objects.equals(isDeleteOwnSos, sosPolicy.isDeleteOwnSos))
            return false;
        return Objects.equals(isDeleteAnySos, sosPolicy.isDeleteAnySos);
    }

    @Override
    public int hashCode() {
        int result = isReadSosList != null ? isReadSosList.hashCode() : 0;
        result = 31 * result + (isReadFullSosList != null ? isReadFullSosList.hashCode() : 0);
        result = 31 * result + (isCreateSos != null ? isCreateSos.hashCode() : 0);
        result = 31 * result + (isUpdateOwnSos != null ? isUpdateOwnSos.hashCode() : 0);
        result = 31 * result + (isUpdateAnySos != null ? isUpdateAnySos.hashCode() : 0);
        result = 31 * result + (isDeleteOwnSos != null ? isDeleteOwnSos.hashCode() : 0);
        result = 31 * result + (isDeleteAnySos != null ? isDeleteAnySos.hashCode() : 0);
        return result;
    }
}