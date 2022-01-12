package com.nsia.officems.taqnin.workflow;

import com.nsia.officems.taqnin.step.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow,Long> {
    List<Workflow> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
