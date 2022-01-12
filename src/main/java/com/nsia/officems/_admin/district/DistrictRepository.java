package com.nsia.officems._admin.district;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
    public List<District> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

	public List<District> findByProvince_id(Long id);
}
