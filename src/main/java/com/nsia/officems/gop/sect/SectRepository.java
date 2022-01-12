package com.nsia.officems.gop.sect;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SectRepository extends JpaRepository<Sect, Long> {
    public List<Sect> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
