package com.openhelp.group.model;

import com.openhelp.group.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
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
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "members_limit")
    private Integer membersLimit;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "closed_at")
    private Timestamp closedAt;

    @Column(name = "expired_at")
    private Timestamp expiredAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @ToString.Exclude
    @OneToMany(mappedBy="group", fetch = FetchType.LAZY)
    private List<Membership> memberships;

    @ToString.Exclude
    @OneToMany(mappedBy="group", fetch = FetchType.LAZY)
    private List<Request> requests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!Objects.equals(id, group.id)) return false;
        if (!Objects.equals(userId, group.userId)) return false;
        if (!Objects.equals(title, group.title)) return false;
        if (!Objects.equals(membersLimit, group.membersLimit)) return false;
        if (group.status != status) return false;
        if (!Objects.equals(description, group.description)) return false;
        if (!Objects.equals(latitude, group.latitude)) return false;
        if (!Objects.equals(longitude, group.longitude)) return false;
        if (!Objects.equals(createdAt, group.createdAt)) return false;
        if (!Objects.equals(updatedAt, group.updatedAt)) return false;
        if (!Objects.equals(closedAt, group.closedAt)) return false;
        if (!Objects.equals(expiredAt, group.expiredAt)) return false;
        return Objects.equals(deletedAt, group.deletedAt);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (membersLimit != null ? membersLimit.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (closedAt != null ? closedAt.hashCode() : 0);
        result = 31 * result + (expiredAt != null ? expiredAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
