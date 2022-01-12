package com.nsia.officems.gop.profile_fired_type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileFiredTypeRepository extends JpaRepository<ProfileFiredType, Long> {
    public List<ProfileFiredType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
