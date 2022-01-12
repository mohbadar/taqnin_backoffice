package com.nsia.officems._admin.country;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    public List<Country> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
