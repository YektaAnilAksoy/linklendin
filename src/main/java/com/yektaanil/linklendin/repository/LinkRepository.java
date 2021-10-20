package com.yektaanil.linklendin.repository;

import com.yektaanil.linklendin.entity.Link;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkRepository extends JpaRepository<Link, Long>, JpaSpecificationExecutor<Link> {

    List<Link> findAllByUserId(Long userId);
}