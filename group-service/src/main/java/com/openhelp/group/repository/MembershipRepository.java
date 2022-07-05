package com.openhelp.group.repository;

import com.openhelp.group.model.Membership;
import com.openhelp.group.repository.filter.MembershipFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

public interface MembershipRepository
        extends JpaRepository<Membership, Long>, JpaSpecificationExecutor<Membership> {

    @Query("UPDATE Membership m SET m.deletedAt = :deletedAt WHERE m.id = :id")
    void updateDeletedAtById(@NotNull Long id, @NotNull Timestamp deletedAt);

    record MembershipSpecification(@NotNull MembershipFilter filter) implements Specification<Membership> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Membership> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getUserId().ifPresent(userId ->
                    exps.add(builder.equal(root.get("userId"), userId)));
            filter.getGroup().ifPresent(group ->
                    exps.add(builder.equal(root.get("group"), group)));
            filter.getCreatedFrom().ifPresent(createdFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("createdAt"), createdFrom)));
            filter.getCreatedTo().ifPresent(createdTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("createdAt"), createdTo)));
            filter.getUpdatedFrom().ifPresent(updatedFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("updatedAt"), updatedFrom)));
            filter.getUpdatedTo().ifPresent(updatedTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("updatedAt"), updatedTo)));
            filter.getDeletedFrom().ifPresent(deletedFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("deletedAt"), deletedFrom)));
            filter.getDeletedTo().ifPresent(deletedTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("deletedAt"), deletedTo)));

            return predicate;
        }
    }
}
