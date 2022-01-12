package com.nsia.officems._admin.province;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    public List<Province> findAllByCountry_id(Long id);

	public List<Province> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
}