package com.nsia.officems.taqnin.decision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecisionRepository extends JpaRepository<Decision,Long> {
    public List<Decision> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
