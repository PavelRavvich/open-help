package com.openhelp.group.repository;

import com.openhelp.group.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MembershipRepository
        extends JpaRepository<Membership, Long>, JpaSpecificationExecutor<Membership> {
}
