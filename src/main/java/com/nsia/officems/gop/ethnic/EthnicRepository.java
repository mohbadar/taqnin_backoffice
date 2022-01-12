package com.nsia.officems.gop.ethnic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EthnicRepository extends JpaRepository<Ethnic, Long> {
    public List<Ethnic> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
