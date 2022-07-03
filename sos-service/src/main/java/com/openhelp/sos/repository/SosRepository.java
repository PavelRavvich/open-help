package com.openhelp.sos.repository;

import com.openhelp.sos.model.Sos;
import com.openhelp.sos.repository.filter.SosFilter;
import com.openhelp.sos.utils.Utils;
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
public interface SosRepository
        extends JpaRepository<Sos, Long>, JpaSpecificationExecutor<Sos> {

    @Query("UPDATE Sos s SET s.deletedAt = :deletedAt WHERE s.id = :id")
    void updateDeletedAtById(@NotNull Long id, @NotNull Timestamp deletedAt);

    record SosSpecification(@NotNull SosFilter filter) implements Specification<Sos> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Sos> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getUserId().ifPresent(userId ->
                    exps.add(builder.equal(root.get("userId"), userId)));
            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")), Utils.toLikeLower(title))));
            filter.getStatus().ifPresent(status ->
                    exps.add(builder.equal(root.get("status"), status)));
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

            return predicate;
        }
    }
}
