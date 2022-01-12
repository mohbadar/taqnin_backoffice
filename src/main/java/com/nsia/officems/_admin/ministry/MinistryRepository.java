package com.nsia.officems._admin.ministry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MinistryRepository extends JpaRepository<Ministry, Long> {
    public List<Ministry> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
