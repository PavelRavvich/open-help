package com.openhelp.group.repository;

import com.openhelp.group.model.Request;
import com.openhelp.group.repository.filter.RequestFilter;
import com.openhelp.group.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface RequestRepository
        extends JpaRepository<Request, Long>, JpaSpecificationExecutor<Request> {

    record RequestSpecification(@NotNull RequestFilter filter) implements Specification<Request> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Request> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getGroup().ifPresent(group ->
                    exps.add(builder.equal(root.get("group"), group)));
            filter.getUserId().ifPresent(userId ->
                    exps.add(builder.equal(root.get("userId"), userId)));
            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")),
                            Utils.toLikeLower(title))));
            filter.getDescription().ifPresent(description ->
                    exps.add(builder.like(builder.lower(root.get("description")),
                            Utils.toLikeLower(description))));
            filter.getStatus().ifPresent(status ->
                    exps.add(builder.equal(root.get("status"), status)));
            filter.getUserId().ifPresent(userId ->
                    exps.add(builder.equal(root.get("userId"), userId)));
            filter.getCreatedFrom().ifPresent(createdFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("createdAt"), createdFrom)));
            filter.getCreatedTo().ifPresent(createdTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("createdAt"), createdTo)));
            filter.getUpdatedFrom().ifPresent(updatedFrom ->
                    exps.add(builder.greaterThanOrEqualTo(root.get("updatedAt"), updatedFrom)));
            filter.getUpdatedTo().ifPresent(updatedTo ->
                    exps.add(builder.lessThanOrEqualTo(root.get("updatedAt"), updatedTo)));

            return predicate;
        }
    }
}
