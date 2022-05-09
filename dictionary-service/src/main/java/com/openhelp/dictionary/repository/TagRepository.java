package com.openhelp.dictionary.repository;

import com.openhelp.dictionary.model.Tag;
import com.openhelp.dictionary.repository.filter.TagFilter;
import com.openhelp.dictionary.utils.Utils;
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
public interface TagRepository
        extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

    @Query("UPDATE Tag t SET t.deletedAt = :deletedAt WHERE t.id = :id")
    void updateDeletedAtById(@NotNull Long id, @NotNull Timestamp deletedAt);

    record TagSpecification(@NotNull TagFilter filter) implements Specification<Tag> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Tag> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")), Utils.toLikeLower(title))));

            return predicate;
        }
    }
}
