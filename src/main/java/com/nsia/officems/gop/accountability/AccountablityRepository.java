package com.nsia.officems.gop.accountability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountablityRepository extends JpaRepository<Accountability, Long> {
    public List<Accountability> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<Accountability> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByEndDateDesc(Long id);

}
