package com.nsia.officems.gop.nationality;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<Nationality, Long> {
    public List<Nationality> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
