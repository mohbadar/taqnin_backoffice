package com.nsia.officems._admin.commission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Long> {
    public List<Commission> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
