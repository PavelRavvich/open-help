package com.openhelp.dictionary.repository;

import com.openhelp.dictionary.model.Country;
import com.openhelp.dictionary.repository.filter.CountryFilter;
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
public interface CountryRepository
        extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

    record CountrySpecification(@NotNull CountryFilter filter) implements Specification<Country> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<Country> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getTitle().ifPresent(title ->
                    exps.add(builder.like(builder.lower(root.get("title")), Utils.toLikeLower(title))));
            filter.getCountryCode().ifPresent(countryCode ->
                    exps.add(builder.like(builder.lower(root.get("countryCode")), Utils.toLikeLower(countryCode))));
            filter.getPhoneCode().ifPresent(phoneCode ->
                    exps.add(builder.like(builder.lower(root.get("phoneCode")), Utils.toLikeLower(phoneCode))));
            filter.getIsoCode().ifPresent(isoCode ->
                    exps.add(builder.like(builder.lower(root.get("isoCode")), Utils.toLikeLower(isoCode))));

            return predicate;
        }
    }
}
