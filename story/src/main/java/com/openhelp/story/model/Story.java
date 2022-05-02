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

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "current_location_id")
    private Long currentLocationId;

    @Column(name = "exodus_location_id")
    private Long exodusLocationId;

    @Column(name = "target_location_id")
    private Long targetLocationId;
}