package com.openhelp.story.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

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
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

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

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "executor_id")
    private Long executorId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!title.equals(task.title)) return false;
        if (!status.equals(task.status)) return false;
        if (!description.equals(task.description)) return false;
        if (!createdAt.equals(task.createdAt)) return false;
        if (!updatedAt.equals(task.updatedAt)) return false;
        if (!closedAt.equals(task.closedAt)) return false;
        if (!authorId.equals(task.authorId)) return false;
        return executorId.equals(task.executorId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + updatedAt.hashCode();
        result = 31 * result + closedAt.hashCode();
        result = 31 * result + authorId.hashCode();
        result = 31 * result + executorId.hashCode();
        return result;
    }
}
