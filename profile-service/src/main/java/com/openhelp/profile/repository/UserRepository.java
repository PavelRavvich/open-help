package com.openhelp.profile.repository;

import com.openhelp.profile.model.User;
import com.openhelp.profile.repository.filter.UserFilter;
import com.openhelp.profile.utils.HqlUtils;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Pavel Ravvich.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @NotNull
    @EntityGraph(value = "user[roles]")
    Optional<User> findById(@NotNull Long userId);

    @EntityGraph(value = "user[roles]")
    User findByUsername(@NonNull String username);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :userId")
    void updatePasswordById(@NotNull Long userId, @NotNull String password);

    @Modifying
    @Query("UPDATE User u SET u.nickname = :nickname WHERE u.id = :userId")
    void updateNicknameById(@NotNull Long userId, @NotNull String nickname);

    @Modifying
    @Query("UPDATE User u SET u.isEnabled = :isEnabled"
            + " WHERE u.activationCode = :activationCode AND u.isEnabled = FALSE")
    void updateIsEnabledByActivationCode(@NotNull Boolean isEnabled, @NotNull UUID activationCode);

    @NotNull
    @Override
    @EntityGraph(value = "user[roles]")
    Page<User> findAll(@Nullable Specification<User> spec, @NotNull Pageable pageable);

    record UserSpecification(@NonNull UserFilter filter) implements Specification<User> {

        @NotNull
        @Override
        public Predicate toPredicate(@NotNull Root<User> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.isEnabled().ifPresent(isEnabled ->
                    exps.add(builder.equal(root.get("isEnabled"), isEnabled)));

            filter.getUsername().ifPresent(username ->
                    exps.add(builder.like(builder.lower(root.get("username")), HqlUtils.toLikeLower(username))));

            filter.getNickname().ifPresent(nickname ->
                    exps.add(builder.like(builder.lower(root.get("nickname")), HqlUtils.toLikeLower(nickname))));

            filter.getRoles().ifPresent(roles -> {
                Predicate[] predicates = roles.stream()
                        .map(role -> builder.equal(root.join("roles"), role))
                        .toArray(Predicate[]::new);
                exps.add(builder.or(predicates));
            });

            return predicate;
        }
    }
}