package com.nsia.officems.gop.gender;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {

	public List<Gender> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
}
