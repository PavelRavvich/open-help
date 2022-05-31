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
@Table(name = "story_policies")
public class StoryPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_read_story_list")
    private Boolean isReadStoryList;

    @Column(name = "is_read_full_story_list")
    private Boolean isReadFullStoryList;

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

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoryPolicy that = (StoryPolicy) o;

        if (!Objects.equals(isReadStoryList, that.isReadStoryList))
            return false;
        if (!Objects.equals(isReadFullStoryList, that.isReadFullStoryList))
            return false;
        if (!Objects.equals(isCreateStory, that.isCreateStory))
            return false;
        if (!Objects.equals(isUpdateOwnStory, that.isUpdateOwnStory))
            return false;
        if (!Objects.equals(isUpdateAnyStory, that.isUpdateAnyStory))
            return false;
        if (!Objects.equals(isDeleteOwnStory, that.isDeleteOwnStory))
            return false;
        return Objects.equals(isDeleteAnyStory, that.isDeleteAnyStory);
    }

    @Override
    public int hashCode() {
        int result = isReadStoryList != null ? isReadStoryList.hashCode() : 0;
        result = 31 * result + (isReadFullStoryList != null ? isReadFullStoryList.hashCode() : 0);
        result = 31 * result + (isCreateStory != null ? isCreateStory.hashCode() : 0);
        result = 31 * result + (isUpdateOwnStory != null ? isUpdateOwnStory.hashCode() : 0);
        result = 31 * result + (isUpdateAnyStory != null ? isUpdateAnyStory.hashCode() : 0);
        result = 31 * result + (isDeleteOwnStory != null ? isDeleteOwnStory.hashCode() : 0);
        result = 31 * result + (isDeleteAnyStory != null ? isDeleteAnyStory.hashCode() : 0);
        return result;
    }
}
