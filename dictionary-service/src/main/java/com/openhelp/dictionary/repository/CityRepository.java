package com.openhelp.dictionary.repository;

import com.openhelp.dictionary.model.City;
import com.openhelp.dictionary.repository.filter.CityFilter;
import com.openhelp.dictionary.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
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

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface CityRepository
        extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    record CitySpecification(@NotNull CityFilter filter) implements Specification<City> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<City> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")), Utils.toLikeLower(title))));
            filter.getPhoneCode().ifPresent(phoneCode ->
                    exps.add(builder.like(builder.lower(root.get("phoneCode")), Utils.toLikeLower(phoneCode))));
            filter.getCountry().ifPresent(country ->
                    exps.add(builder.equal(root.join("country"), country)));

            return predicate;
        }
    }
}
