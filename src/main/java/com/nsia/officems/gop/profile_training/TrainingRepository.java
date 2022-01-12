package com.nsia.officems.gop.profile_training;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface TrainingRepository extends JpaRepository<Training, Long>, RevisionRepository<Training, Long, Integer>   {
    public List<Training> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByEndDateDesc(Long id);

}
