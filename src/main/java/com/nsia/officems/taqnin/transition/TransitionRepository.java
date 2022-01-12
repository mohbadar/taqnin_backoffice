package com.nsia.officems.taqnin.transition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransitionRepository extends JpaRepository<Transition,Long> {
     List<Transition> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
     List<Transition> findByIdIn(List<Long> transitions);
     List<Transition> findByWorkflow_idAndDeletedFalse(long id);
}
