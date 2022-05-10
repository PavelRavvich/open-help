package com.openhelp.story.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

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
@Table(name = "stories")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "closed_at")
    private Timestamp closedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "current_location_id")
    private Long currentLocationId;

    @Column(name = "exodus_location_id")
    private Long exodusLocationId;

    @Column(name = "target_location_id")
    private Long targetLocationId;

    @ToString.Exclude
    @OneToMany(mappedBy="story", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Story story = (Story) o;

        if (!id.equals(story.id)) return false;
        if (!title.equals(story.title)) return false;
        if (!type.equals(story.type)) return false;
        if (!status.equals(story.status)) return false;
        if (!description.equals(story.description)) return false;
        if (!createdAt.equals(story.createdAt)) return false;
        if (!updatedAt.equals(story.updatedAt)) return false;
        if (!closedAt.equals(story.closedAt)) return false;
        if (!authorId.equals(story.authorId)) return false;
        if (!currentLocationId.equals(story.currentLocationId)) return false;
        if (!exodusLocationId.equals(story.exodusLocationId)) return false;
        return targetLocationId.equals(story.targetLocationId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + updatedAt.hashCode();
        result = 31 * result + closedAt.hashCode();
        result = 31 * result + authorId.hashCode();
        result = 31 * result + currentLocationId.hashCode();
        result = 31 * result + exodusLocationId.hashCode();
        result = 31 * result + targetLocationId.hashCode();
        return result;
    }
}