package com.nsia.officems.gop.personal_crime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalCrimeRepository extends JpaRepository<PersonalCrime, Long> {
    public List<PersonalCrime> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(Long id);

}
