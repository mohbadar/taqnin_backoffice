package com.nsia.officems.gop.profile_travel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    public List<Travel> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(Long id);

}
