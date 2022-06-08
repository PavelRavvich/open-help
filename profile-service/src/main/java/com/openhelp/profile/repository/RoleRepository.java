package com.openhelp.profile.repository;

import com.openhelp.profile.model.Role;
import com.openhelp.profile.repository.filter.RoleFilter;
import com.openhelp.profile.utils.HqlUtils;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface RoleRepository
        extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @NotNull
    @EntityGraph(value = "role.access")
    Optional<Role> findById(@NotNull Long id);

    Optional<Role> findBySystemName(@NotNull String systemName);

    record RoleSpecification(@NonNull RoleFilter filter) implements Specification<Role> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Role> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")), HqlUtils.toLikeLower(title))));

            return predicate;
        }
    }
}
