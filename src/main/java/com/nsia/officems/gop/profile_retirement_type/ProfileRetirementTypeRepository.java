package com.nsia.officems.gop.profile_retirement_type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRetirementTypeRepository  extends JpaRepository<ProfileRetirementType, Long> {
    public List<ProfileRetirementType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
