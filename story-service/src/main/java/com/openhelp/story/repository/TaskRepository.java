package com.openhelp.story.repository;

import com.openhelp.story.model.Task;
import com.openhelp.story.repository.filter.TaskFilter;
import com.openhelp.story.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface TaskRepository
        extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @Query("UPDATE Task t SET t.deletedAt = :deletedAt WHERE t.id = :id")
    void updateDeletedAtById(@NotNull Long id, @NotNull Timestamp deletedAt);

    record TaskSpecification(@NotNull TaskFilter filter) implements Specification<Task> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Task> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")), Utils.toLikeLower(title))));
            filter.getStory().ifPresent(story -> exps.add(builder.equal(root.join("story"), story)));
            filter.getStatus().ifPresent(status -> exps.add(builder.equal(root.get("status"), status)));
            filter.getType().ifPresent(type -> exps.add(builder.equal(root.get("type"), type)));
            filter.getAuthorId().ifPresent(id -> exps.add(builder.equal(root.get("authorId"), id)));
            filter.getExecutorId().ifPresent(id -> exps.add(builder.equal(root.get("executorId"), id)));

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
            filter.getDeletedTo().ifPresent(deletedTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("deletedAt"), deletedTo)));

            return predicate;
        }
    }
}
