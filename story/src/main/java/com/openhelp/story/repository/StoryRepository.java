package com.openhelp.story.repository;

import com.openhelp.story.model.Story;
import com.openhelp.story.repository.filter.StoryFilter;
import com.openhelp.story.utils.SqlUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface StoryRepository
        extends JpaRepository<Story, Long>, JpaSpecificationExecutor<Story> {

    @Query("UPDATE Story s SET s.deletedAt = :deletedAt WHERE s.id = :id")
    void updateDeletedAtById(@NotNull Long id, @NotNull Timestamp deletedAt);

    record StorySpecification(@NotNull StoryFilter filter) implements Specification<Story> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Story> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")), SqlUtils.toLikeLower(title))));
            filter.getStatus().ifPresent(status -> exps.add(builder.equal(root.get("status"), status)));
            filter.getType().ifPresent(type -> exps.add(builder.equal(root.get("type"), type)));

            filter.getCurrentLocationId().ifPresent(id -> exps.add(builder.equal(root.get("currentLocationId"), id)));
            filter.getExodusLocationId().ifPresent(id -> exps.add(builder.equal(root.get("exodusLocationId"), id)));
            filter.getTargetLocationId().ifPresent(id -> exps.add(builder.equal(root.get("targetLocationId"), id)));

            filter.getCreatedFrom().ifPresent(createdFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("createdAt"), createdFrom)));
            filter.getCreatedTo().ifPresent(createdTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("createdAt"), createdTo)));
            filter.getUpdatedFrom().ifPresent(updatedFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("updatedAt"), updatedFrom)));
            filter.getUpdatedTo().ifPresent(updatedTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("updatedAt"), updatedTo)));
            filter.getClosedFrom().ifPresent(closedFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("closedAt"), closedFrom)));
            filter.getClosedTo().ifPresent(closedTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("closedAt"), closedTo)));
            filter.getDeletedFrom().ifPresent(deletedFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("deletedAt"), deletedFrom)));
            filter.getDeletedTo().ifPresent(deletedAt ->
                    exps.add(builder.lessThanOrEqualTo(root.get("deletedAt"), deletedAt)));

            return predicate;
        }
    }
}
