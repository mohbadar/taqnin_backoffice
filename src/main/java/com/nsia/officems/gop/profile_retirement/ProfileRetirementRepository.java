package com.nsia.officems.gop.profile_retirement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRetirementRepository extends JpaRepository<ProfileRetirement, Long> {
    public List<ProfileRetirement> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<ProfileRetirement> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByType_idDesc(Long id);
}
