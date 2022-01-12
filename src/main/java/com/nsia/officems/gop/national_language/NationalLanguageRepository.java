package com.nsia.officems.gop.national_language;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalLanguageRepository extends JpaRepository<NationalLanguage, Long> {
    public List<NationalLanguage> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
	public List<NationalLanguage> findByIdIn(List<Long> languages);
}
