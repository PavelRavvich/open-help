package com.openhelp.group.repository;

import com.openhelp.group.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface GroupRepository
        extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {
}
