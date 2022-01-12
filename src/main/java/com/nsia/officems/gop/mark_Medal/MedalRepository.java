package com.nsia.officems.gop.mark_Medal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedalRepository extends JpaRepository<Medal, Long> {
    public List<Medal> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

}
