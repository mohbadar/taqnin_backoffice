package com.nsia.officems._admin.authority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    public List<Authority> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
