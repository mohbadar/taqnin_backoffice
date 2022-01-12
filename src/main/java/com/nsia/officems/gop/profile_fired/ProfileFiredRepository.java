package com.nsia.officems.gop.profile_fired;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileFiredRepository extends JpaRepository<ProfileFired, Long> {
    public List<ProfileFired> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

}
