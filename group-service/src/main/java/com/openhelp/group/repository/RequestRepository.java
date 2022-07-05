package com.openhelp.group.repository;

import com.openhelp.group.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RequestRepository
        extends JpaRepository<Request, Long>, JpaSpecificationExecutor<Request> {
}
