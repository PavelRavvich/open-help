package com.openhelp.dictionary.repository;

import com.openhelp.dictionary.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface TagRepository
        extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

    @Query("UPDATE Tag t SET t.deletedAt = :deletedAt WHERE t.id = :id")
    void updateDeletedAtById(@NotNull Long id, @NotNull Timestamp deletedAt);
}
