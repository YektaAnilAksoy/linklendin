package com.yektaanil.linklendin.repository;

import com.yektaanil.linklendin.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findUserByUsername(final String username);

    boolean existsByUsernameOrEmail(final String username, final String email);
}