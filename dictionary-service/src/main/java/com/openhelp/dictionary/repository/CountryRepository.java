package com.openhelp.dictionary.repository;

import com.openhelp.dictionary.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface CountryRepository
        extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
}
